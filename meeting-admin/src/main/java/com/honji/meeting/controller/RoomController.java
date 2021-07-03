package com.honji.meeting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.Participant;
import com.honji.meeting.entity.Room;
import com.honji.meeting.entity.RoomParticipant;
import com.honji.meeting.model.DataGridResult;
import com.honji.meeting.model.OccupiedRoomVO;
import com.honji.meeting.model.RoomVO;
import com.honji.meeting.service.IParticipantService;
import com.honji.meeting.service.IRoomParticipantService;
import com.honji.meeting.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-08-03
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IRoomParticipantService roomParticipantService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private HttpSession session;

    @GetMapping("/get")
    public RoomVO get(@RequestParam Long id) {
        Room room = roomService.getById(id);
        RoomVO roomVO = new RoomVO(room.getId(), room.getUserId(), room.getType());
        QueryWrapper<RoomParticipant> wrapper = new QueryWrapper<>();
        wrapper.eq("room_id", room.getId());
        List<RoomParticipant> roomParticipants = roomParticipantService.list(wrapper);
        List<Long> ids = new ArrayList<>();
        for (RoomParticipant roomParticipant : roomParticipants) {
            ids.add(roomParticipant.getParticipantId());
        }
        List<Participant> participants = new ArrayList(participantService.listByIds(ids));
        roomVO.parse(participants);
        return roomVO;
    }

    @GetMapping("/list")
    public DataGridResult list(@RequestParam int offset, @RequestParam int limit,
                               @RequestParam String search) {

        IPage<RoomVO> roomVOPage = new Page<>(offset / limit + 1, limit);
        Admin admin = (Admin) session.getAttribute("admin");
        return  new DataGridResult(roomService.getForIndex(roomVOPage, admin.getType(), search));

    }


    @PostMapping("/save")
    public void save(@ModelAttribute Room room) {
        if (room.getId() == null) {
            roomService.add(room);
        } else {
            roomService.merge(room);
        }
    }

    @PostMapping("/remove")
    public void remove(@RequestParam String[] ids) {
        roomService.delete(ids);
    }

    /**
     * 查找要删除的用户被其它用户选择为同住人的房间信息
     * @param userId
     * @return
     */
    @GetMapping("/hasRoom")
    public OccupiedRoomVO hasRoom(@RequestParam Long userId) {
        return roomService.getOccupiedRoom(userId);

    }

}

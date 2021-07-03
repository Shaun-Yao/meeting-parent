package com.honji.meeting.controller;


import com.honji.meeting.entity.Participant;
import com.honji.meeting.entity.Room;
import com.honji.meeting.model.UserSessionVO;
import com.honji.meeting.service.IParticipantService;
import com.honji.meeting.service.IRoomParticipantService;
import com.honji.meeting.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private IRoomParticipantService roomParticipantService;

    @Autowired
    private HttpSession session;

    @GetMapping("/toAdd")
    public String toAdd(Model model) {
        UserSessionVO user = (UserSessionVO) session.getAttribute("user");
        Long userId = user.getId();
//        QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id", userId);
//        List<Participant> participants = participantService.list(queryWrapper);
        List<Participant> participants = participantService.getAvailable(userId);
        List<Participant> unavailableParticipants = participantService.getUnAvailable(userId);
        participants.addAll(unavailableParticipants);//加上已经分配的参与人
        //已经分配房间的参与人名字加上（已分配）标识
        for (Participant participant : unavailableParticipants) {
            participant.setName(participant.getName().concat("（已分配）"));
        }
        List<Participant> participants4Area = participantService.getByArea(userId);
        List<Participant> children = participantService.getChildren(userId);
        model.addAttribute("participants", participants);
        model.addAttribute("participants4Area", participants4Area);
        model.addAttribute("children", children);
        return "roomForm";
    }

    @GetMapping("/toEdit")
    public String toEdit(@RequestParam Long id, Model model) {
        UserSessionVO user = (UserSessionVO) session.getAttribute("user");
        Long userId = user.getId();
        Room room = roomService.getById(id);
        List<Participant> roomParticipants = participantService.listByRoom(room.getId());
        room.setParticipants(roomParticipants);

        List<Participant> participants = participantService.getByArea(userId);
        List<Participant> participants4Area = participantService.getByArea(userId);
        List<Participant> children = participantService.getChildren(userId);
        //修改的时候要把已经选的两个人员加进列表
        participants.add(roomParticipants.get(0));
        participants.add(roomParticipants.get(1));
        participants4Area.add(roomParticipants.get(0));
        participants4Area.add(roomParticipants.get(1));
        if (roomParticipants.size() == 3) {//如果有第三个人员则加进儿童列表
            children.add(roomParticipants.get(2));
        }

        model.addAttribute("participants", participants);
        model.addAttribute("participants4Area", participants4Area);
        model.addAttribute("children", children);
        model.addAttribute("room", room);
        return "roomForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Room room) {
        if (room.getId() == null) {
            roomService.add(room);
        } else {
            roomService.merge(room);
        }

        return "redirect:/room/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        UserSessionVO user = (UserSessionVO) session.getAttribute("user");

        model.addAttribute("rooms", roomService.list(user.getId()));
        return "rooms";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        roomService.delete(id);
        return "redirect:/room/list";
    }



}

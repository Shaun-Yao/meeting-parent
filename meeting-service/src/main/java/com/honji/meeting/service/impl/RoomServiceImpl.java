package com.honji.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.meeting.entity.Participant;
import com.honji.meeting.entity.Room;
import com.honji.meeting.entity.RoomParticipant;
import com.honji.meeting.mapper.RoomMapper;
import com.honji.meeting.mapper.RoomParticipantMapper;
import com.honji.meeting.model.OccupiedRoomVO;
import com.honji.meeting.model.RoomVO;
import com.honji.meeting.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-07-24
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomParticipantMapper roomParticipantMapper;


    @Override
    public List<RoomVO> list(Long userId) {
        return roomMapper.selectListVO(userId);
    }

    @Override
    @Transactional
    public void add(Room room) {
        //List<RoomParticipant> roomParticipants = new ArrayList<>();
        roomMapper.insert(room);

        for (Participant participant : room.getParticipants()) {
            Long participantId = participant.getId();
            if (participantId == null) {
                continue;
            }
            RoomParticipant roomParticipant = new RoomParticipant();
            roomParticipant.setRoomId(room.getId());
            roomParticipant.setParticipantId(participant.getId());
            //roomParticipants.add(roomParticipant);
            roomParticipantMapper.insert(roomParticipant);
        }
    }

    @Override
    @Transactional
    public void merge(Room room) {

        QueryWrapper<RoomParticipant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id", room.getId());
        //List<RoomParticipant> roomParticipants = roomParticipantMapper.selectList(queryWrapper);
        roomParticipantMapper.delete(queryWrapper);
        roomMapper.updateById(room);

        for (Participant participant : room.getParticipants()) {
            Long participantId = participant.getId();
            if (participantId == null) {
                continue;
            }
            RoomParticipant roomParticipant = new RoomParticipant();
            roomParticipant.setRoomId(room.getId());
            roomParticipant.setParticipantId(participant.getId());
            roomParticipantMapper.insert(roomParticipant);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        QueryWrapper<RoomParticipant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id", id);
        roomParticipantMapper.delete(queryWrapper);
        roomMapper.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        for (String id : ids) {
            QueryWrapper<RoomParticipant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("room_id", id);
            roomParticipantMapper.delete(queryWrapper);
            roomMapper.deleteById(id);
        }

    }

    @Override
    public OccupiedRoomVO getOccupiedRoom(Long userId) {
        return roomMapper.selectOccupiedRoom(userId);
    }

    @Override
    public IPage<RoomVO> getForIndex(IPage<RoomVO> page, String shopType, String search) {
        return roomMapper.selectForIndex(page, shopType, search);
    }
}

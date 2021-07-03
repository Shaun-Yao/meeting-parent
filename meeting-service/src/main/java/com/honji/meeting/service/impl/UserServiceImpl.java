package com.honji.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.meeting.entity.*;
import com.honji.meeting.mapper.*;
import com.honji.meeting.model.UserSessionVO;
import com.honji.meeting.model.UserVO;
import com.honji.meeting.service.IUserService;
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
 * @since 2019-03-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ParticipantMapper participantMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RoomParticipantMapper roomParticipantMapper;

    public void insert(User user) {
        userMapper.insert(user);
        List<Participant> participants = user.getParticipants();
        for (Participant participant : participants) {

            participant.setUserId(user.getId());
            participantMapper.insert(participant);
        }
    }

    @Override
    public UserSessionVO getForSession(String openId) {
        return userMapper.selectForSession(openId);
    }

    @Override
    public List<UserVO> getUsers(Page<UserVO> page, String shopType, String shopCode) {
        return userMapper.selectUsers(page, shopType, shopCode);
    }


    @Override
    @Transactional
    public void delete(Long id) {

        QueryWrapper<Schedule> scheduleQueryWrapper = new QueryWrapper<>();
        scheduleQueryWrapper.eq("user_id", id);
        scheduleMapper.delete(scheduleQueryWrapper);//删除用户行程

        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper.eq("user_id", id);
        List<Room> rooms = roomMapper.selectList(roomQueryWrapper);

        //删除用户创建的所有房间
        for (Room room : rooms) {
            Long roomId = room.getId();
            QueryWrapper<RoomParticipant> roomParticipantQueryWrapper = new QueryWrapper<>();
            roomParticipantQueryWrapper.eq("room_id", roomId);
            roomParticipantMapper.delete(roomParticipantQueryWrapper);
            roomMapper.deleteById(roomId);
        }

        QueryWrapper<Participant> participantQueryWrapper = new QueryWrapper<>();
        participantQueryWrapper.eq("user_id", id);
        participantMapper.delete(participantQueryWrapper);//删除用户添加的参与人

        userMapper.deleteById(id);

    }
}

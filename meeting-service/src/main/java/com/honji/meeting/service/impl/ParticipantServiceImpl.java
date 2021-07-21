package com.honji.meeting.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.meeting.entity.Participant;
import com.honji.meeting.mapper.ParticipantMapper;
import com.honji.meeting.model.ParticipantVO;
import com.honji.meeting.service.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-07-07
 */
@Service
public class ParticipantServiceImpl extends ServiceImpl<ParticipantMapper, Participant> implements IParticipantService {

    @Autowired
    private ParticipantMapper participantMapper;

    @Override
    public IPage<ParticipantVO> getForIndex(Page<Participant> page, String shopType, String search) {
        return participantMapper.selectForIndex(page, shopType, search);
    }

    @Override
    public List<Participant> listAvailable(String shopType) {
        return participantMapper.selectAvailableByShopType(shopType);
    }


    @Override
    public List<Participant> getAvailable(Long userId) {
        return participantMapper.selectAvailable(userId);
    }

    @Override
    public List<Participant> getUnAvailable(Long userId) {
        return participantMapper.selectUnAvailable(userId);
    }

    @Override
    public List<Participant> getByArea(Long userId) {
        return participantMapper.selectByArea(userId);
    }

    @Override
    public List<Participant> getChildren(Long userId) {
        return participantMapper.selectChildren(userId);
    }

    @Override
    public List<Participant> listByRoom(Long roomId) {
        return participantMapper.selectByRoom(roomId);
    }


}

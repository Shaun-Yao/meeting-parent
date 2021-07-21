package com.honji.meeting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.meeting.entity.Participant;
import com.honji.meeting.model.ParticipantVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-07-07
 */
public interface IParticipantService extends IService<Participant> {


    IPage<ParticipantVO> getForIndex(Page<Participant> page, String shopType, String search);
    //    List<Participant> listAvailable(Long userId);
    List<Participant> listAvailable(String shopType);
//    List<Participant> listAvailableChildren();
    List<Participant> getAvailable(Long userId);
    List<Participant> getUnAvailable(Long userId);
    List<Participant> getByArea(Long userId);

    List<Participant> getChildren(Long userId);

    List<Participant> listByRoom(Long roomId);
}

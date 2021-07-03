package com.honji.meeting.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.meeting.entity.Schedule;
import com.honji.meeting.mapper.ScheduleMapper;
import com.honji.meeting.model.ScheduleVO;
import com.honji.meeting.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2019-07-20
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements IScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public IPage<ScheduleVO> getForIndex(IPage<Schedule> page, String shopType, String search) {
        return scheduleMapper.selectForIndex(page, shopType, search);
    }
}

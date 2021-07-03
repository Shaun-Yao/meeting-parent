package com.honji.meeting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.meeting.entity.Schedule;
import com.honji.meeting.model.ScheduleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-07-20
 */
public interface IScheduleService extends IService<Schedule> {

    IPage<ScheduleVO> getForIndex(IPage<Schedule> page, String shopType, String search);
}

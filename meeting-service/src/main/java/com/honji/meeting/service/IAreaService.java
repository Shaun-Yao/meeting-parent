package com.honji.meeting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.Area;
import com.honji.meeting.entity.Schedule;
import com.honji.meeting.model.AreaVO;
import com.honji.meeting.model.ScheduleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2021-07-13
 */
public interface IAreaService extends IService<Area> {

    IPage<AreaVO> listForIndex(IPage<Area> page, String type);
}

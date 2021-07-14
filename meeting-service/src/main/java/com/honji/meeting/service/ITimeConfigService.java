package com.honji.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.meeting.entity.Area;
import com.honji.meeting.entity.TimeConfig;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2021-07-13
 */
public interface ITimeConfigService extends IService<TimeConfig> {
    TimeConfig getConfig(String shopCode);
}

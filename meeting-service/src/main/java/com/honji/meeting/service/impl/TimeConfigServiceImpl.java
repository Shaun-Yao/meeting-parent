package com.honji.meeting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.meeting.entity.Area;
import com.honji.meeting.entity.TimeConfig;
import com.honji.meeting.mapper.AreaMapper;
import com.honji.meeting.mapper.TimeConfigMapper;
import com.honji.meeting.service.IAreaService;
import com.honji.meeting.service.ITimeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yao
 * @since 2021-07-13
 */
@Service
public class TimeConfigServiceImpl extends ServiceImpl<TimeConfigMapper, TimeConfig> implements ITimeConfigService {

    @Autowired
    private TimeConfigMapper timeConfigMapper;

    @Override
    public TimeConfig getConfig(String shopCode) {
        return timeConfigMapper.selectConfig(shopCode);
    }
}

package com.honji.meeting.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.Area;
import com.honji.meeting.entity.Schedule;
import com.honji.meeting.mapper.AdminMapper;
import com.honji.meeting.mapper.AreaMapper;
import com.honji.meeting.model.AreaVO;
import com.honji.meeting.service.IAdminService;
import com.honji.meeting.service.IAreaService;
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
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements IAreaService {

    @Autowired
    private AreaMapper areaMapper;


    @Override
    public IPage<AreaVO> listForIndex(IPage<Area> page, String type) {
        return areaMapper.selectForIndex(page, type);
    }
}

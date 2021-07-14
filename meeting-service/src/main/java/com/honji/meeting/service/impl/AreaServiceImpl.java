package com.honji.meeting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.Area;
import com.honji.meeting.mapper.AdminMapper;
import com.honji.meeting.mapper.AreaMapper;
import com.honji.meeting.service.IAdminService;
import com.honji.meeting.service.IAreaService;
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

}

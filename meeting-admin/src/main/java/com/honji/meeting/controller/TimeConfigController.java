package com.honji.meeting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.TimeConfig;
import com.honji.meeting.entity.TimeConfig;
import com.honji.meeting.enums.ShopTypeEnum;
import com.honji.meeting.model.DataGridResult;
import com.honji.meeting.service.ITimeConfigService;
import com.honji.meeting.service.ITimeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2021-07-13
 */
@RestController
@RequestMapping("/time-config")
public class TimeConfigController {

    @Autowired
    private ITimeConfigService timeConfigService;

    @Autowired
    private HttpSession session;

    @GetMapping("/get")
    public TimeConfig get(@RequestParam Long id) {
        return timeConfigService.getById(id);
    }

    @GetMapping("/list")
    public DataGridResult list(@RequestParam(defaultValue = "0") int offset, @RequestParam int limit) {
        Admin admin = (Admin) session.getAttribute("admin");
        IPage<TimeConfig> timeConfigPage = new Page<>(offset / limit + 1, limit);
        QueryWrapper<TimeConfig> queryWrapper = new QueryWrapper<>();
        String type = admin.getType();
        if (!StringUtils.isEmpty(type)) {
            queryWrapper.eq("type", admin.getType());
        }
//        queryWrapper.like("code", search);
        return new DataGridResult(timeConfigService.page(timeConfigPage, queryWrapper));

    }


    @PostMapping("/save")
    public boolean save(@ModelAttribute TimeConfig timeConfig) {
        Admin admin = (Admin) session.getAttribute("admin");
        timeConfig.setType(ShopTypeEnum.get(admin.getType()));
        return timeConfigService.saveOrUpdate(timeConfig);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        List<String> resultList = new ArrayList<>(ids.length);
        Collections.addAll(resultList, ids);
        return timeConfigService.removeByIds(resultList);
    }


}

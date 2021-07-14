package com.honji.meeting.controller;


import com.honji.meeting.entity.SysConfig;
import com.honji.meeting.service.IScheduleTimeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-10-18
 */
@Controller
@RequestMapping("/schedule-time-config")
public class ScheduleTimeConfigController {

    @Autowired
    private IScheduleTimeConfigService scheduleTimeConfigService;

    @PostMapping("/update")
    @ResponseBody
    public boolean update(@ModelAttribute SysConfig scheduleTimeConfig) {

        return scheduleTimeConfigService.updateById(scheduleTimeConfig);
    }
}

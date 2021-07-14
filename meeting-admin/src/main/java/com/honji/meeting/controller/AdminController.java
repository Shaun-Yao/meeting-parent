package com.honji.meeting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.honji.meeting.entity.*;
import com.honji.meeting.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-08-03
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private ISignUpSwitchService signUpSwitchService;

    @Autowired
    private IScheduleTimeConfigService scheduleTimeConfigService;

    @Autowired
    private ITimeConfigService timeConfigService;

    @Autowired
    private IAreaService areaService;

    @Autowired
    private HttpSession session;

    @GetMapping("/config")
    public String config(Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        String type = admin.getType();
        QueryWrapper<SignUpSwitch> signUpSwitchQueryWrapper = new QueryWrapper();
        QueryWrapper<SysConfig> scheduleTimeConfigQueryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(type)) {
            signUpSwitchQueryWrapper.eq("shop_type", admin.getType());
            scheduleTimeConfigQueryWrapper.eq("shop_type", admin.getType());
        }

        List<SignUpSwitch> switches = signUpSwitchService.list(signUpSwitchQueryWrapper);
        List<SysConfig> timeConfigs = scheduleTimeConfigService.list(scheduleTimeConfigQueryWrapper);
        model.addAttribute("switches", switches);
        model.addAttribute("timeConfigs", timeConfigs);
        return "config";
    }

    @GetMapping("/toLogin")
    public String toLogin() {

        return "login";
    }

    @GetMapping("/adminlte")
    public String adminlte() {

        return "adminlte";
    }


    @GetMapping("/index")
    public String layout() {

        return "index";
    }


    @GetMapping("/shop")
    public String shop(Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();

        String type = admin.getType();
        if (!StringUtils.isEmpty(type)) {
            queryWrapper.eq("type", admin.getType());
        }
        List<Area> areas = areaService.list(queryWrapper);
        model.addAttribute("areas", areas);
        return "shop";
    }

    @GetMapping("/participant")
    public String participant() {
        return "participant";
    }

    @GetMapping("/schedule")
    public String schedule() {
        return "schedule";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/room")
    public String room(Model model) {
//        Admin admin = (Admin) session.getAttribute("admin");
//        String type = admin.getType();
//        List<Participant> participants = participantService.getAvailable(type);
//        List<Participant> children = participantService.getAvailableChildren(type);
//        model.addAttribute("participants", participants);
//        model.addAttribute("children", children);
        return "room";
    }

    @GetMapping("/area")
    public String area(Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        QueryWrapper<TimeConfig> queryWrapper = new QueryWrapper<>();

        String type = admin.getType();
        if (!StringUtils.isEmpty(type)) {
            queryWrapper.eq("type", admin.getType());
        }
        List<TimeConfig> timeConfigs = timeConfigService.list(queryWrapper);
        model.addAttribute("timeConfigs", timeConfigs);
        return "area";
    }

    @GetMapping("/time-config")
    public String timeConfig() {
        return "time-config";
    }


    @ResponseBody
    @PostMapping("/login")
    public boolean login(@RequestParam String account, @RequestParam String password) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        Admin admin = adminService.getOne(queryWrapper);
        if (admin != null) {
            if (password.equals(admin.getPassword())) {
                session.setAttribute("admin", admin);
                return true;
            }
        }

        return false;
    }

    @ResponseBody
    @PostMapping("/logout")
    public void logout() {
        session.removeAttribute("admin");

    }

}

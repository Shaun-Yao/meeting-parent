package com.honji.meeting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.ScheduleTimeConfig;
import com.honji.meeting.entity.SignUpSwitch;
import com.honji.meeting.service.IAdminService;
import com.honji.meeting.service.IScheduleTimeConfigService;
import com.honji.meeting.service.ISignUpSwitchService;
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
    private HttpSession session;

    @GetMapping("/config")
    public String config(Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        String type = admin.getType();
        QueryWrapper<SignUpSwitch> signUpSwitchQueryWrapper = new QueryWrapper();
        QueryWrapper<ScheduleTimeConfig> scheduleTimeConfigQueryWrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(type)) {
            signUpSwitchQueryWrapper.eq("shop_type", admin.getType());
            scheduleTimeConfigQueryWrapper.eq("shop_type", admin.getType());
        }

        List<SignUpSwitch> switches = signUpSwitchService.list(signUpSwitchQueryWrapper);
        List<ScheduleTimeConfig> timeConfigs = scheduleTimeConfigService.list(scheduleTimeConfigQueryWrapper);
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


    @GetMapping("/table")
    public String table() {

        return "table";
    }


    @GetMapping("/another")
    public String another() {

        return "another";
    }

    @GetMapping("/shop")
    public String shop() {
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

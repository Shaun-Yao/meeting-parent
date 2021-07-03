package com.honji.meeting.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.User;
import com.honji.meeting.model.DataGridResult;
import com.honji.meeting.model.UserVO;
import com.honji.meeting.service.IParticipantService;
import com.honji.meeting.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  UserController前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-10-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private HttpSession session;

    @GetMapping("/get")
    public User get(@RequestParam Long id) {
        return userService.getById(id);
    }


    @GetMapping("/list")
    public DataGridResult list(@RequestParam(defaultValue = "0") int offset, @RequestParam int limit,
                               @RequestParam String search) {

        Admin admin = (Admin) session.getAttribute("admin");
        Page<UserVO> userPage = new Page<>(offset / limit + 1, limit);
        List<UserVO> userVOS = userService.getUsers(userPage, admin.getType(), search);
        userPage.setRecords(userVOS);

        return  new DataGridResult(userPage);

    }

    @PostMapping("/remove")
    public void remove(@RequestParam Long id) {
        userService.delete(id);
    }
}

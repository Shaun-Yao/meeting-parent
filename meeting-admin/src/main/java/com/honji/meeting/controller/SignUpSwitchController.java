package com.honji.meeting.controller;


import com.honji.meeting.entity.SignUpSwitch;
import com.honji.meeting.service.ISignUpSwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-10-17
 */
@Controller
@RequestMapping("/sign-up-switch")
public class SignUpSwitchController {

    @Autowired
    private ISignUpSwitchService signUpSwitchService;

    @GetMapping("/index")
    public String index(Model model) {
        List<SignUpSwitch> switches = signUpSwitchService.list();
        //System.out.println(switches.get(0).getType());
        model.addAttribute("switches", switches);
        return "sign-up-switch";
    }

    @PostMapping("/change")
    @ResponseBody
    public boolean change(@RequestParam Long id, @RequestParam boolean onOff) {
        SignUpSwitch signUpSwitch = signUpSwitchService.getById(id);
        signUpSwitch.setOnOff(onOff);
//        UpdateWrapper<SignUpSwitch> wrapper = new UpdateWrapper<>();
//        wrapper.eq();
        return signUpSwitchService.updateById(signUpSwitch);
    }

}

package com.honji.meeting.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.honji.meeting.entity.*;
import com.honji.meeting.model.UserSessionVO;
import com.honji.meeting.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private IScheduleTimeConfigService scheduleTimeConfigService;

    @Autowired
    private HttpSession session;

    @Autowired
    private ISignUpSwitchService signUpSwitchService;

    @GetMapping("/toApply")
    public String index(@RequestParam(required = false) String code,
                        @RequestParam(required = false)  String prefix, Model model) {

        UserSessionVO user = (UserSessionVO) session.getAttribute("user");
        if (prefix == null) {
            prefix = user.getShopType();
        }
        //model.addAttribute("prefix", prefix);

        //User user = null;
        QueryWrapper<SignUpSwitch> susQueryWrapper = new QueryWrapper();
        susQueryWrapper.eq("shop_type", prefix);
        SignUpSwitch signUpSwitch = signUpSwitchService.getOne(susQueryWrapper);

        QueryWrapper<SysConfig> stQueryWrapper = new QueryWrapper();
        stQueryWrapper.eq("shop_type", prefix);
        SysConfig scheduleTimeConfig = scheduleTimeConfigService.getOne(stQueryWrapper);


//        if (session.getAttribute("prefix") == null) {
//            session.setAttribute("prefix", prefix);
//        }

        model.addAttribute("prefix", prefix);
        model.addAttribute("onOff", signUpSwitch.isOnOff());
        model.addAttribute("deadline", scheduleTimeConfig.getDeadline());

/*

        if( userId != null ) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", userId);
            user = userService.getOne(queryWrapper);
            model.addAttribute("user", user);
        }
*/


        if (user != null) {
            //Shop shop = shopService.getById(user.getShopId());
            QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", user.getId());
            List<Participant> participants = participantService.list(queryWrapper);

            QueryWrapper<Schedule> scheduleQueryWrapper = new QueryWrapper<>();
            scheduleQueryWrapper.eq("user_id", user.getId());
            Schedule schedule = scheduleService.getOne(scheduleQueryWrapper);

            //model.addAttribute("shopCode", shop.getCode());
            model.addAttribute("participants", participants);
            model.addAttribute("schedule", schedule);
        }
        return "applyForm";
    }



    @GetMapping("/toEdit")
    public String toEdit() {
        //this.initSession(code);
        //session.setAttribute("userId", "sdgfdhgdfhert");
        return "editApplyInfo";
    }



    @PostMapping("/apply")
    public String apply(@ModelAttribute User user) {
        //log.info("openId=={}", user.getOpenId());
        //?????????????????????????????????openId???????????????????????????????????????
        if (StringUtils.isEmpty(user.getOpenId())) {
            log.error("opendId ??????");
            return "error";
        }
        Shop shop = shopService.getByCode(user.getShopCode());
        user.setShopId(shop.getId());
        boolean result = userService.saveOrUpdate(user);

        Object userSession = session.getAttribute("user");
        if( userSession == null && result ) { //????????????????????????session
            UserSessionVO sessionVO = new UserSessionVO(user.getId(), shop.getId(), shop.getCode(), shop.getType().getCode());
            //UserSessionVO sessionVO = new UserSessionVO(user.getId(), shop);
            session.setAttribute("user", sessionVO);
            return "redirect:/participant/toAdd";//???id ?????????
        }

        return "redirect:/user/toApply";

    }


    @ResponseBody
    @PostMapping("/updateShopCode")
    public boolean updateShopCode(@RequestParam String shopCode) {
        UserSessionVO userSessionVO = (UserSessionVO) session.getAttribute("user");
        User user = userService.getById(userSessionVO.getId());
        Shop shop = shopService.getByCode(shopCode);

        user.setShopId(shop.getId());
        boolean result = userService.updateById(user);
        if (result) { //???????????????????????????session
            userSessionVO.setShopId(shop.getId());
            userSessionVO.setShopCode(shop.getCode());
            userSessionVO.setShopType(shop.getType().getCode());
            session.setAttribute("user", userSessionVO);
        }

        return result;
    }


    /**
     * ?????????????????????
     * @param shopCode
     * @return
     */
    @ResponseBody
    @GetMapping("/isSignUp")
    public boolean isSignUp(@RequestParam String shopCode) {
        Shop shop = shopService.getByCode(shopCode);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id", shop.getId());
        List<User> users = userService.list(queryWrapper);
        if (users.size() > 0) {//????????????
            return true;
        }

        return false;
    }
}
package com.honji.meeting.controller;


import com.honji.meeting.entity.Shop;
import com.honji.meeting.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-07-08
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @GetMapping("/get")
    public Shop get(@RequestParam String code) {

        Shop shop = shopService.getByCode(code);
        return shop;
    }
}

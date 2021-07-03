package com.honji.meeting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.Shop;
import com.honji.meeting.model.DataGridResult;
import com.honji.meeting.service.IShopService;
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
 * @since 2019-08-03
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @Autowired
    private HttpSession session;

    @GetMapping("/get")
    public Shop get(@RequestParam Long id) {
        return shopService.getById(id);
    }

    @GetMapping("/list")
    public DataGridResult list(@RequestParam(defaultValue = "0") int offset, @RequestParam int limit,
                               @RequestParam String search) {
        Admin admin = (Admin) session.getAttribute("admin");
        IPage<Shop> shopPage = new Page<>(offset / limit + 1, limit);
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        String type = admin.getType();
        if (!StringUtils.isEmpty(type)) {
            queryWrapper.eq("type", admin.getType());
        }
        queryWrapper.like("code", search);
        return new DataGridResult(shopService.page(shopPage, queryWrapper));

    }


    @PostMapping("/save")
    public boolean save(@ModelAttribute Shop shop) {
        return shopService.saveOrUpdate(shop);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        List<String> resultList = new ArrayList<>(ids.length);
        Collections.addAll(resultList, ids);
        return shopService.removeByIds(resultList);
    }


}

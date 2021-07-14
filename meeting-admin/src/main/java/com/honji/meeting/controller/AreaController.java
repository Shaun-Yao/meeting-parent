package com.honji.meeting.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.Area;
import com.honji.meeting.enums.ShopTypeEnum;
import com.honji.meeting.model.DataGridResult;
import com.honji.meeting.service.IAreaService;
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
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @Autowired
    private HttpSession session;

    @GetMapping("/get")
    public Area get(@RequestParam Long id) {
        return areaService.getById(id);
    }

    @GetMapping("/list")
    public DataGridResult list(@RequestParam(defaultValue = "0") int offset, @RequestParam int limit) {
        Admin admin = (Admin) session.getAttribute("admin");
        IPage<Area> areaPage = new Page<>(offset / limit + 1, limit);
        QueryWrapper<Area> queryWrapper = new QueryWrapper<>();
        String type = admin.getType();
        if (!StringUtils.isEmpty(type)) {
            queryWrapper.eq("type", admin.getType());
        }
//        queryWrapper.like("code", search);
        return new DataGridResult(areaService.page(areaPage, queryWrapper));

    }


    @PostMapping("/save")
    public boolean save(@ModelAttribute Area area) {
        Admin admin = (Admin) session.getAttribute("admin");
        area.setType(ShopTypeEnum.get(admin.getType()));
        return areaService.saveOrUpdate(area);
    }
/*

    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        List<String> resultList = new ArrayList<>(ids.length);
        Collections.addAll(resultList, ids);
        return areaService.removeByIds(resultList);
    }
*/


}

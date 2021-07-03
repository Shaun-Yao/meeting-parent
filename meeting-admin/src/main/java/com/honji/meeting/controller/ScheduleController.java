package com.honji.meeting.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.Schedule;
import com.honji.meeting.model.DataGridResult;
import com.honji.meeting.service.IScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-08-03
 */
@Slf4j
@RestController
@RequestMapping("/schedule")
public class ScheduleController {


    @Value("${web.upload-path}")
    private String uploadPath;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private IScheduleService scheduleService;

    @Autowired
    private HttpSession session;


    @GetMapping("/get")
    public Schedule get(@RequestParam Long id) {
        return scheduleService.getById(id);
    }

    @GetMapping("/list")
    public DataGridResult list(@RequestParam(defaultValue = "0") int offset, @RequestParam int limit,
                               @RequestParam String search) {
        Admin admin = (Admin) session.getAttribute("admin");
        IPage<Schedule> schedulePage = new Page<>(offset / limit + 1, limit);
        return new DataGridResult(scheduleService.getForIndex(schedulePage, admin.getType(), search));

    }


    @PostMapping("/save")
    public boolean save(@ModelAttribute Schedule schedule) {
        return scheduleService.saveOrUpdate(schedule);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        List<String> resultList = new ArrayList<>(ids.length);
        Collections.addAll(resultList, ids);
        return scheduleService.removeByIds(resultList);

    }

    @ResponseBody
    @PostMapping("/upload")
    public String upload(@RequestParam String userId, @RequestParam("file") MultipartFile file) {
        //UserSessionVO user = (UserSessionVO) session.getAttribute("user");
        //文件名由用户id-时间组成，如150-20200408095444759.png
        StringBuffer newFileName = new StringBuffer(userId).append("-");
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();//原文件名
                log.info("图片文件名称 {}", fileName);
                byte[] bytes = file.getBytes();
                int idx = fileName.lastIndexOf(".");
                String suffix= fileName.substring(idx); //文件后缀
                String time = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
                //newFileName = time.concat(suffix);
                String filePath = uploadPath.concat(newFileName.append(time).append(suffix).toString());
                Path path = Paths.get(filePath);
                Files.write(path, bytes);
                log.info("{} 上传行程图片成功", newFileName);

            } catch (IOException e) {
                log.info("上传行程图片失败");
                e.printStackTrace();
            }
        }
        return newFileName.toString();
    }

    @RequestMapping("show")
    public ResponseEntity showPhotos(@RequestParam String fileName){

        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + uploadPath.concat(fileName)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}

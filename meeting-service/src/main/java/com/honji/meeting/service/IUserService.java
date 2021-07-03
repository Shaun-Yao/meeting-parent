package com.honji.meeting.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.meeting.entity.User;
import com.honji.meeting.model.UserSessionVO;
import com.honji.meeting.model.UserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-03-01
 */
public interface IUserService extends IService<User> {

    void insert(User user);
    UserSessionVO getForSession(String openId);
    List<UserVO> getUsers(Page<UserVO> page, String shopType, String shopCode);
    void delete(Long id);
}

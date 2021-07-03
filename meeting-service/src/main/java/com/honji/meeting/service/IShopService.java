package com.honji.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.meeting.entity.Shop;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-07-08
 */
public interface IShopService extends IService<Shop> {
    Shop getByCode(String code);
    Shop getByUserId(Long userId);
}

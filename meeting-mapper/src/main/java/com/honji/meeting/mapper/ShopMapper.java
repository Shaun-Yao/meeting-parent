package com.honji.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honji.meeting.entity.Shop;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2019-07-08
 */
public interface ShopMapper extends BaseMapper<Shop> {

    @Select("SELECT * FROM shop WHERE id = (SELECT shop_id FROM `user` WHERE id = #{user_id})")
    Shop selectByUserId(Long userId);

}

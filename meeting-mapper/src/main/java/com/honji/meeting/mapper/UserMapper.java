package com.honji.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.honji.meeting.entity.User;
import com.honji.meeting.model.UserSessionVO;
import com.honji.meeting.model.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT `user`.id, shop.id as shopId, shop.code as shopCode, shop.type as shopType FROM `user` " +
            "LEFT JOIN shop ON `user`.shop_id = shop.id  " +
            "WHERE open_id = #{openId}")
    UserSessionVO selectForSession(@Param("openId")String openId);

    @Select({"<script>",
            "SELECT `user`.id, shop.type as shopType, shop.`code` as shopCode, shop.`name` as shopName,  ",
            "area.name as area, shop.small_area as smallArea, group_concat(participant.name) as userNames FROM `user`",
            "LEFT JOIN participant ON `user`.id = participant.user_id ",
            "LEFT JOIN shop ON `user`.shop_id = shop.id ",
            "LEFT JOIN area ON shop.area = area.code ",
            "WHERE 1=1 ",
            "<if test='shopType!=null and shopType!=\"\"'>",
            "AND shop.type = #{shopType} ",
            "</if>",
            "<if test='shopCode!=null and shopCode!=\"\"'>",
            "AND shop.code like CONCAT('%', #{shopCode}, '%')",
            "</if>",
            "GROUP BY `user`.id",
            "</script>"})
    List<UserVO> selectUsers(Page<UserVO> page, @Param("shopType") String shopType, @Param("shopCode") String shopCode);
}

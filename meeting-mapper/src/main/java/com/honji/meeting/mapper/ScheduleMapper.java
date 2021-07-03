package com.honji.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.honji.meeting.entity.Schedule;
import com.honji.meeting.model.ScheduleVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2019-07-20
 */
public interface ScheduleMapper extends BaseMapper<Schedule> {

    @Select({"<script>",
            "SELECT sc.id, sc.user_id as userId, sc.arrived_travel_mode as arrivedTravelMode,",
            "sc.leaved_travel_mode as leavedTravelMode, sc.arrived_num as arrivedNum,",
            "sc.leaved_num as leavedNum, sc.arrived_time as arrivedTime, sc.leaved_time as leavedTime,",
            "sc.arrived_pick_up_station as arrivedPickUpStation, sc.leaved_pick_up_location as leavedPickUpLocation,",
            "sc.leaved_station as leavedStation, shop.type as shopType, shop.`code` as shopCode, shop.`name` as shopName, ",
            "group_concat(participant.name) as userNames, shop.area, shop.small_area as smallArea FROM `schedule` sc ",
            "LEFT JOIN `user` ON sc.user_id = `user`.id ",
            "LEFT JOIN participant ON sc.user_id = participant.user_id ",
            "LEFT JOIN shop ON `user`.shop_id = shop.id ",
            "WHERE 1=1 ",
            "<if test='shopType!=null and shopType!=\"\"'>",
            "AND shop.type = #{shopType} ",
            "</if>",
            "GROUP BY `user`.id",
            "<if test='search!=null and search!=\"\"'>",
            " HAVING (userId like CONCAT('%', #{search}, '%') or userNames like CONCAT('%', #{search}, '%')",
            " or shopCode like CONCAT('%', #{search}, '%') or shopName like CONCAT('%', #{search}, '%'))",
            "</if>",
            "</script>"})
    IPage<ScheduleVO> selectForIndex(IPage<Schedule> page, @Param("shopType") String shopType, @Param("search") String search);

}

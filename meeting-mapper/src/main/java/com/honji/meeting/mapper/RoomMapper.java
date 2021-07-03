package com.honji.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.honji.meeting.entity.Room;
import com.honji.meeting.model.OccupiedRoomVO;
import com.honji.meeting.model.RoomVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2019-07-24
 */
public interface RoomMapper extends BaseMapper<Room> {

    @Select({"<script>",
            "SELECT room.id, room.type, group_concat(participant.name) as userNames FROM room ",
            "LEFT JOIN room_participant rp ON room.id = rp.room_id ",
            "LEFT JOIN participant ON rp.participant_id = participant.id ",
            "WHERE room.user_id = #{userId} ",
            "GROUP BY room_id",
            "</script>"})
    List<RoomVO> selectListVO(@Param("userId") Long userId);



    @Select({"<script>",
            "SELECT room.id, room.user_id as userId, room.type, shop.code as shopCode, shop.name as shopName,",
            "group_concat(participant.name) as userNames FROM room_participant rp ",
            "LEFT JOIN participant ON rp.participant_id = participant.id ",
            "LEFT JOIN room ON rp.room_id = room.id ",
            "LEFT JOIN `user` ON room.user_id = `user`.id ",
            "LEFT JOIN shop ON `user`.shop_id = shop.id ",
            "WHERE 1=1 ",
            "<if test='shopType!=null and shopType!=\"\"'>",
            "AND shop.type = #{shopType} ",
            "</if>",
            "GROUP BY room_id",
            "<if test='search!=null and search!=\"\"'>",
            " HAVING (userId like CONCAT('%', #{search}, '%') or userNames like CONCAT('%', #{search}, '%')",
            " or shopCode like CONCAT('%', #{search}, '%') or shopName like CONCAT('%', #{search}, '%'))",
            "</if>",
            "</script>"})
    IPage<RoomVO> selectForIndex(IPage<RoomVO> page, @Param("shopType") String shopType, @Param("search") String search);

    /**
     * 查找要删除的用户被其它用户选择为同住人的房间信息
     * @param userId
     * @return
     */
    @Select("SELECT room.user_id as userId, room.id, participant.`name` FROM room \n" +
            "LEFT JOIN room_participant rp on room.id = rp.room_id \n" +
            "LEFT JOIN participant on rp.participant_id = participant.id \n" +
            "WHERE room.user_id != #{userId} and participant_id in \n" +
            "(SELECT id FROM participant WHERE user_id = #{userId})\n" +
            " LIMIT 1")
    OccupiedRoomVO selectOccupiedRoom(@Param("userId") Long userId );

}

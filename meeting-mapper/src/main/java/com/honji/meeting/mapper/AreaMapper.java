package com.honji.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.honji.meeting.entity.Admin;
import com.honji.meeting.entity.Area;
import com.honji.meeting.entity.Schedule;
import com.honji.meeting.entity.TimeConfig;
import com.honji.meeting.model.AreaVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2021-07-13
 */
public interface AreaMapper extends BaseMapper<Area> {

    @Select({"<script>",
            "SELECT area.*, tc.name as time FROM area",
            " LEFT JOIN time_config tc on area.time_config_id = tc.id",
            " WHERE 1 = 1",
            "<if test='type!=null and type!=\"\"'>",
            " AND area.type = #{type} ",
            "</if>",
            "</script>"
    })
    IPage<AreaVO> selectForIndex(IPage<Area> page, @Param("type")String type);
}

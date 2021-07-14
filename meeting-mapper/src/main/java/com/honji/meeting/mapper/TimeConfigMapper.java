package com.honji.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honji.meeting.entity.TimeConfig;
import com.honji.meeting.model.UserSessionVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yao
 * @since 2021-07-13
 */
public interface TimeConfigMapper extends BaseMapper<TimeConfig> {

    @Select({"SELECT tc.* FROM time_config tc",
            " JOIN area on tc.id = area.time_config_id",
            " JOIN shop on area.code = shop.area",
            " WHERE shop.code = #{shopCode}"
    })
    TimeConfig selectConfig(@Param("shopCode")String shopCode);

}

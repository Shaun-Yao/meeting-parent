package com.honji.meeting.entity;

import com.honji.meeting.enums.ShopTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yao
 * @since 2019-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Area extends IdEntity {

    private String code;
    private String name;

    private Long timeConfigId;

    private ShopTypeEnum type;
}

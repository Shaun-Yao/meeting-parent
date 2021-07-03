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
public class Shop extends IdEntity {


    private String code;

    private String name;

    private String area;

    private String smallArea;


    private ShopTypeEnum type;
    private String situation;
    private String mode;
    private String franchisee;
}

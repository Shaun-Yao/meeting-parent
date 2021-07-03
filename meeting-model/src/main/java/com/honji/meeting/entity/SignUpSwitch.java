package com.honji.meeting.entity;

import com.honji.meeting.enums.ShopTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yao
 * @since 2019-10-17
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SignUpSwitch extends IdEntity {


    private ShopTypeEnum shopType;

    private boolean onOff;


}

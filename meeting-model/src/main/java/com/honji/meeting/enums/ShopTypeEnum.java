package com.honji.meeting.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum ShopTypeEnum {


    ZHI_YING("Z", "直营"),
    DAI_LI("D", "代理加盟"),
    LIAN_YING("L", "联营");

    ShopTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ShopTypeEnum get(String code) {
        for(ShopTypeEnum ste :ShopTypeEnum.values()) {
            if (code.equals(ste.code)) {
                return ste;
            }
        }
        return null;
    }

    @EnumValue
    private final String code;
    private final String desc;


    public String getCode() {
        return code;
    }

    //@JsonValue
    public String getDesc() {
        return desc;
    }

}

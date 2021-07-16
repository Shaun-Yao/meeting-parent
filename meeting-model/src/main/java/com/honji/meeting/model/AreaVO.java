package com.honji.meeting.model;

import com.honji.meeting.enums.ShopTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AreaVO {

    private  Long id;
    private String type;
    private String code;
    private String name;
    private String time;

}

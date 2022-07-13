package com.honji.meeting.model;

import com.honji.meeting.enums.SexEnum;
import com.honji.meeting.enums.ShopTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipantVO {

    private  Long id;
    private Long userId;
    private String name;
    private String idNumber;
    private String mobile;
    private SexEnum sex;
    private boolean attendTraining;
    private boolean attendHotSpring;
    private ShopTypeEnum shopType;
    private String shopCode;
    private String shopName;
    private String area;
    private String smallArea;



}

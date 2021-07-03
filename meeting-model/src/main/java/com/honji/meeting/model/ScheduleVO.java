package com.honji.meeting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.honji.meeting.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScheduleVO {

    private Long id;
    private Long userId;
    private TravelModeEnum arrivedTravelMode;
    private TravelModeEnum leavedTravelMode;
    private String arrivedNum;
    private String leavedNum;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime arrivedTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime leavedTime;
    private StationEnum arrivedPickUpStation;
    private LeavePickUpLocationEnum leavedPickUpLocation;
    private LeaveStationEnum leavedStation;
    private ShopTypeEnum shopType;
    private String shopCode;
    private String shopName;
    private String area;
    private String smallArea;
    private String userNames;


}

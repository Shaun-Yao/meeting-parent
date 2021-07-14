package com.honji.meeting.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.honji.meeting.enums.ShopTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yao
 * @since 2019-10-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TimeConfig extends IdEntity {


    private String name;

    private ShopTypeEnum type;
    /**
     * 来程开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime arriveStartTime;

    /**
     * 来程结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime arriveEndTime;

    /**
     * 返程开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime leaveStartTime;

    /**
     * 返程结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime leaveEndTime;




}

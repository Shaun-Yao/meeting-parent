package com.honji.meeting.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {
    private String openId;
    private Long shopId;

    @TableField(exist = false)
    private String shopCode;

    @TableField(exist = false)
    private List<Participant> participants = new ArrayList<>();


}

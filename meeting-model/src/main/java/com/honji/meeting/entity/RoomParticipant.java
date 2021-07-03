package com.honji.meeting.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yao
 * @since 2019-07-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoomParticipant extends BaseEntity {


    private Long roomId;

    private Long participantId;

    @TableField(exist = false)
    private Participant participant;

}

package com.honji.meeting.model;

import com.honji.meeting.entity.Participant;
import com.honji.meeting.enums.RoomTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class RoomVO {

    private Long id;
    private Long userId;
    private String shopCode;
    private String shopName;
    private RoomTypeEnum type;
    private String userNames;
    private List<Participant> participants;

    public RoomVO(Long id, Long userId, RoomTypeEnum type) {
        this.id = id;
        this.userId = userId;
        this.type = type;
    }


    public RoomVO(Long id, Long userId, String shopCode, String shopName,
                  RoomTypeEnum type, String userNames) {
        this.id = id;
        this.userId = userId;
        this.shopCode = shopCode;
        this.shopName = shopName;
        this.type = type;
        this.userNames = userNames;
    }

    public void parse(List<Participant> participants) {

        //this.total = participants.size();
        StringBuffer name = new StringBuffer();
        for (Participant participant : participants) {
            name.append(participant.getName());
            name.append(", ");
        }
        this.participants = participants;
        this.userNames = name.toString();
    }

}

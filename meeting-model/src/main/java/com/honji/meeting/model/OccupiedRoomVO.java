package com.honji.meeting.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OccupiedRoomVO {

    private Long id;
    private Long userId;
    private String name;


}

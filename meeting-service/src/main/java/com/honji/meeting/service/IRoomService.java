package com.honji.meeting.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.honji.meeting.entity.Room;
import com.honji.meeting.model.OccupiedRoomVO;
import com.honji.meeting.model.RoomVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yao
 * @since 2019-07-24
 */
public interface IRoomService extends IService<Room> {

    List<RoomVO> list(Long userId);
    void add(Room room);
    void merge(Room room);
    void delete(Long id);
    void delete(String[] ids);

    OccupiedRoomVO getOccupiedRoom(Long userId);
    IPage<RoomVO> getForIndex(IPage<RoomVO> page, String shopType, String search);
}

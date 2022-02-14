package com.ovalor.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.ovalor.vo.RoomInfoVo;

public interface RoomInfoMapper {
	// GetList
	public ArrayList<RoomInfoVo> getRoomList();

	// GetList For Admin
	public ArrayList<RoomInfoVo> getRoomInfoListAdmin();

	// Select
	public RoomInfoVo getRoom(int no);

	// Insert
	public int addRoomInfo(RoomInfoVo roomInfo);

	// Update
	public int modRoomInfo(RoomInfoVo roomInfo);

	// Delete
	public int delRoomInfo(@Param("no") int no, @Param("deleteFlag") String deleteFlag);

}

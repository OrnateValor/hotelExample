package com.ovalor.service;

import java.util.ArrayList;

import com.ovalor.vo.RoomInfoVo;

public interface RoomInfoService {

	// List
	ArrayList<RoomInfoVo> getRoomInfoList();

	// List for Admin
	ArrayList<RoomInfoVo> getRoomInfoListAdmin();

	// Get Room Details
	RoomInfoVo getRoomInfo(int no);

	// Insert
	int addRoomInfo(RoomInfoVo roomInfo);

	// Update
	int modRoomInfo(RoomInfoVo roomInfo);

	// Delete
	int delRoomInfo(int no, String deleteFlag);

}

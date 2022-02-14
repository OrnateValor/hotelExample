package com.ovalor.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ovalor.mapper.RoomInfoMapper;
import com.ovalor.vo.RoomInfoVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomInfoServiceImple implements RoomInfoService {
	private RoomInfoMapper riMapper;

	// GetList
	@Override
	public ArrayList<RoomInfoVo> getRoomInfoList() {
		// return list
		return riMapper.getRoomList();
	}

	// GetList For Admin
	@Override
	public ArrayList<RoomInfoVo> getRoomInfoListAdmin() {
		// TODO Auto-generated method stub
		return riMapper.getRoomInfoListAdmin();
	}

	// Select
	@Override
	public RoomInfoVo getRoomInfo(int no) {
		// return
		return riMapper.getRoom(no);
	}

	// Insert
	@Override
	public int addRoomInfo(RoomInfoVo roomInfo) {
		return riMapper.addRoomInfo(roomInfo);
	}

	// Update
	@Override
	public int modRoomInfo(RoomInfoVo roomInfo) {
		return riMapper.modRoomInfo(roomInfo);
	}

	// Delete
	@Override
	public int delRoomInfo(int no, String deleteFlag) {
		return riMapper.delRoomInfo(no, deleteFlag);
	}

}

package com.ovalor.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ovalor.vo.RoomInfoVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RoomInfoMapperTest {

	@Setter(onMethod_ = @Autowired)
	private RoomInfoMapper riMapper;

	// GetList
	@Test
	public void testGetRoomInfoList() {
		riMapper.getRoomList().forEach(roomInfo -> log.info(roomInfo));
	}

	// Select
	// @Test
	public void testGetRoomInfo() {
		RoomInfoVo room = riMapper.getRoom(5);
		log.info(room);
	}

	// Insert
	// @Test
	public void testAddRoomInfo() {
		RoomInfoVo vo = new RoomInfoVo();
		vo.setRoomNum("822");
		vo.setRoomTitle("2人部屋（ダブルルーム）");
		vo.setMax(4);
		vo.setAdultCost(24);
		vo.setChildCost(12);
		vo.setExplanation("ダブルルームは自宅と同じように静かで居心地の良い、楽しむためのプライベートスポットです。");
		vo.setColorCode("#393939");

		riMapper.addRoomInfo(vo);
		log.info(vo);
	}

	// Update
	// @Test
	public void testModRoomInfo() {
		RoomInfoVo vo = riMapper.getRoom(6);
		vo.setMax(2);
		riMapper.modRoomInfo(vo);
		log.info(riMapper.getRoom(6));
	}
}

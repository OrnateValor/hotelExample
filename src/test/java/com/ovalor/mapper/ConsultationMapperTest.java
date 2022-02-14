package com.ovalor.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ovalor.vo.ConsultationVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ConsultationMapperTest {

	@Setter(onMethod_ = @Autowired)
	private ConsultationMapper cMapper;

	// GetList
	@Test
	public void testGetRoomInfoList() {
		cMapper.getConsultList().forEach(consult -> log.info(consult));
	}

	// Select
	// @Test
	public void testGetRoomInfo() {
		ConsultationVo consult = cMapper.getConsult(6);
		log.info(consult);
	}

	// Insert
	// @Test
	public void testAddRoomInfo() {
		ConsultationVo vo = new ConsultationVo();
		vo.setTitle("TestConsult");
		vo.setContents("相談テーブルのテストデーたです。");
		vo.setName("OrnateValor");
		vo.setLockFlag('1');

		cMapper.addConsult(vo);
		log.info(vo);
	}

	// Update
	// @Test
	public void testModRoomInfo() {
		ConsultationVo vo = cMapper.getConsult(6);
		vo.setLockFlag('0');
		cMapper.modConsult(vo);
		log.info(cMapper.getConsult(6));
	}
}

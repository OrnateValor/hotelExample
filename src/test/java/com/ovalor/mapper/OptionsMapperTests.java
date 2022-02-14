package com.ovalor.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ovalor.vo.OptionsVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class OptionsMapperTests {

	@Setter(onMethod_ = @Autowired)
	private OptionsMapper oMapper;

	// GetList
//	@Test
	public void testGetNoticeList() {
		oMapper.getOptionsList().forEach(options -> log.info(options));
	}

	// Select
//	@Test
	public void testGetNotice() {
		OptionsVo option = oMapper.getOptions(1);
		log.info(option);
	}

	// Insert
//	@Test
	public void testAddNotice() {
		OptionsVo option = new OptionsVo();

		option.setItem("Scotch Blue");
		option.setCost(3600);
		
		oMapper.addOptions(option);
		log.info(option);
	}

	// Update
	@Test
	public void testModNotice() {
		OptionsVo option = oMapper.getOptions(1);
		option.setDeleteFlag('1');
		oMapper.modOptions(option);
	}
}

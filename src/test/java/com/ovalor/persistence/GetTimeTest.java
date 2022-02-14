package com.ovalor.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ovalor.mapper.GetTimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class GetTimeTest {
	@Setter(onMethod_ = @Autowired)
	private GetTimeMapper tMapper;

	@Test
	public void testGetTime() {
		log.info(tMapper.getClass().getName());
		log.info(tMapper.getNow());
	}
}
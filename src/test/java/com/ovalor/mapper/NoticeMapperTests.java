package com.ovalor.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ovalor.vo.NoticeVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoticeMapperTests {

	@Setter(onMethod_ = @Autowired)
	private NoticeMapper nMapper;

	// GetList
//	@Test
	public void testGetNoticeList() {
		nMapper.getNoticeList().forEach(booking -> log.info(booking));
	}

	// Select
//	@Test
	public void testGetNotice() {
		NoticeVo notice = nMapper.getNotice(14);
		log.info(notice);
	}

	// Insert
//	@Test
	public void testAddNotice() {
		NoticeVo notice = new NoticeVo();

		notice.setTitle("予約についてのお知らせ(8棟)");
		notice.setContents("予約についてのお客様へのお知らせです。");

		nMapper.addNotice(notice);
		log.info(notice);
	}

	// Update
//	@Test
	public void testModNotice() {
		NoticeVo notice = nMapper.getNotice(14);
		notice.setDeleteFlag('1');
		nMapper.modNotice(notice);
	}
}

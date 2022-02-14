package com.ovalor.mapper;

import java.util.ArrayList;

import com.ovalor.vo.NoticeVo;

public interface NoticeMapper {
	// GetList
	public ArrayList<NoticeVo> getNoticeList();

	// Select
	public NoticeVo getNotice(int no);

	// Insert
	public int addNotice(NoticeVo notice);

	// Update
	public int modNotice(NoticeVo notice);
}

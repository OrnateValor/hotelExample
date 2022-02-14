package com.ovalor.service;

import java.util.ArrayList;

import com.ovalor.vo.NoticeVo;

public interface NoticeService {
	public ArrayList<NoticeVo> getNoticeList();

	public NoticeVo getNotice(int no);

	public int addNotice(NoticeVo notice);

	public int modNotice(NoticeVo notice);

}

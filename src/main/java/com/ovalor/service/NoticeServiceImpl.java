package com.ovalor.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ovalor.mapper.NoticeMapper;
import com.ovalor.vo.NoticeVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	// 매퍼 선언
	private NoticeMapper nMapper;

	@Override
	public ArrayList<NoticeVo> getNoticeList() {
		// 코드가 어디까지 왔는지 추적하기 위한 출력문
		// System.out.println("getNoticeList()");
		// 반환부
		return nMapper.getNoticeList();
	}

	@Override
	public NoticeVo getNotice(int no) {
		// 추적부
		// System.out.println("getNotice()");

		// 반환부
		return nMapper.getNotice(no);
	}

	@Override
	public int addNotice(NoticeVo notice) {
		// 반환부
		return nMapper.addNotice(notice);

	}

	@Override
	public int modNotice(NoticeVo notice) {
		// 반환부
		return nMapper.modNotice(notice);
	}

}

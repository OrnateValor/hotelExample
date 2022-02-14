package com.ovalor.service;

import java.util.ArrayList;

import com.ovalor.vo.ConsultationVo;

public interface ConsultationService {
	// 리스트
	public ArrayList<ConsultationVo> getConsultationList();

	// 게시글 획득
	public ConsultationVo getConsultation(int no);

	// 게시글 추가
	public int addConsultation(ConsultationVo consultation);

	// 답글 추가
	public int addReply(ConsultationVo consultation);

	// 유사삭제처리
	public int delConsultation(int no);

	// 답글 갯수 카운트
	public int cntReply(int no);
	
	// 그룹오더 수정
	public void upGrgrod(int grno, int grgrod);

}

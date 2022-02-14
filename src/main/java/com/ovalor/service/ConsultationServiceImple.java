package com.ovalor.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ovalor.mapper.ConsultationMapper;
import com.ovalor.vo.ConsultationVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsultationServiceImple implements ConsultationService {
	private ConsultationMapper cMapper;

	// Get List
	@Override
	public ArrayList<ConsultationVo> getConsultationList() {
		// 리스트를 받아서 반환.
		return cMapper.getConsultList();
	}

	// Select
	@Override
	public ConsultationVo getConsultation(int no) {
		// 객체 한개 반환
		return cMapper.getConsult(no);
	}

	// Add
	@Override
	public int addConsultation(ConsultationVo consultation) {
		return cMapper.addConsult(consultation);

	}

	@Override
	public int addReply(ConsultationVo consultation) {
		return cMapper.addReply(consultation);

	}

	// Del
	@Override
	public int delConsultation(int no) {
		return cMapper.delConsult(no);
	}

	// 답변 갯수로 grgrod 결정할거임.
	@Override
	public int cntReply(int no) {
		return Integer.parseInt(cMapper.cntReply(no));
	}

	// 그룹오더 수정
	@Override
	public void upGrgrod(int grno, int grgrod) {
		cMapper.upGrgrod(grno, grgrod);
	}
}

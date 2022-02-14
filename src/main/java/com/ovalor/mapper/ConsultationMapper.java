package com.ovalor.mapper;

import java.util.ArrayList;

import com.ovalor.vo.ConsultationVo;

public interface ConsultationMapper {
	// GetList
	public ArrayList<ConsultationVo> getConsultList();

	// Select
	public ConsultationVo getConsult(int no);

	// Insert
	public int addConsult(ConsultationVo consult);

	// Insert Reply
	public int addReply(ConsultationVo consult);

	// Update
	public int modConsult(ConsultationVo consult);

	// Delete
	public int delConsult(int no);

	// cnt
	public String cntReply(int no);

	// upGrgrod
	public void upGrgrod(int grno, int grgrod);
}

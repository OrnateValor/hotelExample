package com.ovalor.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ConsultationVo {
	int no;
	int grno;
	int grgrod;
	int depth;
	String title;
	String contents;
	String name;
	String passwd;
	char lockFlag;
	char deleteFlag;
	Date createdAt;
	Date updatedAt;
	int buildCode;
}

package com.ovalor.vo;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeVo {
	int no;
	String title;
	String contents;
	char deleteFlag;
	Date createdAt;
	Date updatedAt;
	int buildCode;
}

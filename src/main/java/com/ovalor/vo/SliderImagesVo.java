package com.ovalor.vo;

import java.util.Date;

import lombok.Data;

@Data
public class SliderImagesVo {
	int no;
	String fileName;
	String sortNo;
	char activity;
	char deleteFlag;
	Date createdAt;
	Date updatedAt;
	int buildCode;
}

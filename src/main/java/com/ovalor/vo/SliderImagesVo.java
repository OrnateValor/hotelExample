package com.ovalor.vo;

import java.util.Date;

import lombok.Data;

@Data
public class SliderImagesVo {
	int no;
	String fileName;
	String sortNo;
	char activity = '0';
	char deleteFlag = '0';
	Date createdAt;
	Date updatedAt;
	int buildCode = 8;
}

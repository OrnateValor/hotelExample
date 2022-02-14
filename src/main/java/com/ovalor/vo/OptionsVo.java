package com.ovalor.vo;

import java.util.Date;

import lombok.Data;

@Data
public class OptionsVo {
	int no;
	String item;
	int cost;
	char activity;
	char deleteFlag;
	Date createdAt;
	Date updatedAt;
}

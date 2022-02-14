package com.ovalor.vo;

import java.util.Date;

import lombok.Data;

@Data
public class RoomInfoVo {
	int no;
	String roomNum;
	String roomTitle;
	int max;
	int adultCost;
	int childCost;
	String explanation;
	String images;
	String colorCode;
	String deleteFlag;
	Date createdAt;
	Date updatedAt;
	int buildCode;
}

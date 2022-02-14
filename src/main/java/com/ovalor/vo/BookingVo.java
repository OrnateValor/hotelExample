package com.ovalor.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BookingVo {
	int no;
	int roomNo;
	String name;
	String phone;
	char adult;
	char child;
	String startDate;
	String endDate;
	String options;
	char paymentFlag;
	int totalCost;
	char cancelFlag;
	String bankName;
	String bankBranchCode;
	String bankAccountNumber;
	char deleteFlag;
	Date createdAt;
	Date updatedAt;
	int buildCode;

	// input에 입력된 date형테를 DB길이에 맞춰서 String으로 변환
	public void replaceDate() {
		this.startDate = this.startDate.replace("-", "");
		this.endDate = this.endDate.replace("-", "");
	}

	// DB에 저장된 날짜를 input:type=date 에 맞춰서 바꿔주는 메소드
	public void returnDate() {
		this.startDate = this.startDate.substring(0, 4) + "-" + this.startDate.substring(4, 6) + "-"
				+ this.startDate.substring(6, 8);
		this.endDate = this.endDate.substring(0, 4) + "-" + this.endDate.substring(4, 6) + "-"
				+ this.endDate.substring(6, 8);
		;
	}
}

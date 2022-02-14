package com.ovalor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingReAttr {
	// 예약 수정 및 등록 처리 후 상태 표시
	String stat;
	// 예약정보 담아줄거임.
	BookingVo booking;
}

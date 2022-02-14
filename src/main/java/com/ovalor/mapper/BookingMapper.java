package com.ovalor.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.ovalor.vo.BookingVo;

public interface BookingMapper {

	// 예약 관련. 내 건물번호는 8임.
	// GetList
	public ArrayList<BookingVo> getBookingList();

	// Select
	public ArrayList<BookingVo> getBookingListByName(@Param("name") String name, @Param("phone") String phone);

	public BookingVo getBookingByNo(int no);

	// Insert
	public int addBooking(BookingVo booking);

	// Update
	public int modBooking(BookingVo booking);

	// Payment 만 변경
	public int modBookingPayment(BookingVo booking);

	// Cancel
	public int cancelBooking(BookingVo booking);

	// Delete - 데이터를 실제로 삭제하는 것이 아닌, deleteFalg 만 1로 바꿀 것.
	public int delBooking(BookingVo booking);

	// modify paymentFlag 0 to 1
	public int modPayment(int id);

}

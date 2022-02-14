package com.ovalor.service;

import java.util.ArrayList;

import com.ovalor.vo.BookingVo;

public interface BookingService {

	// GetList
	public ArrayList<BookingVo> getBookingList();

	// Select
	public ArrayList<BookingVo> getBookingListByName(String name, String phone);

	// Insert
	public int addBooking(BookingVo booking);

	// Update
	public int modBooking(BookingVo booking);

	// Delete
	public int cancelBooking(BookingVo booking);

	// Delete
	public int delBooking(BookingVo booking);

	public int modPayment(int id);

	public BookingVo getBookingByNo(int no);
}

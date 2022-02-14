package com.ovalor.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ovalor.mapper.BookingMapper;
import com.ovalor.vo.BookingVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingServiceImple implements BookingService {
	private BookingMapper bMapper;

	// GetList
	@Override
	public ArrayList<BookingVo> getBookingList() {
		// TODO Auto-generated method stub
		return bMapper.getBookingList();
	}

	// Select
	@Override
	public ArrayList<BookingVo> getBookingListByName(String name, String phone) {
		return bMapper.getBookingListByName(name, phone);
	}

	@Override
	public BookingVo getBookingByNo(int no) {
		return bMapper.getBookingByNo(no);
	}

	// Insert
	@Override
	public int addBooking(BookingVo booking) {
		return bMapper.addBooking(booking);
	}

	// Update
	@Override
	public int modBooking(BookingVo booking) {
		return bMapper.modBooking(booking);
	}

	// Update
	public int modBookingPayment(BookingVo booking) {
		return bMapper.modBookingPayment(booking);
	}

	// Cancel
	@Override
	public int cancelBooking(BookingVo booking) {
		return bMapper.cancelBooking(booking);
	}

	// Delete
	@Override
	public int delBooking(BookingVo booking) {
		return bMapper.delBooking(booking);
	}

	// Payment 0 to 1
	@Override
	public int modPayment(int id) {
		return bMapper.modPayment(id);
	}

}

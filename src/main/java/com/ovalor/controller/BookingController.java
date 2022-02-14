package com.ovalor.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ovalor.service.BookingService;
import com.ovalor.service.OptionsService;
import com.ovalor.service.RoomInfoService;
import com.ovalor.vo.BookingReAttr;
import com.ovalor.vo.BookingVo;
import com.ovalor.vo.RoomInfoVo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/booking/*")
@AllArgsConstructor
public class BookingController {
	private BookingService bService;
	private RoomInfoService rService;
	private OptionsService oService;

	// 예약 확인
	@RequestMapping("/main")
	public String bookingMain() {
		log.info("Booking::Main");
		return "/booking/main";
	}

	// 작성 양식
	@RequestMapping("/form")
	public String getForm(@ModelAttribute("roomInfo") RoomInfoVo roomInfo, Model model) {
		log.info("form");
		// System.out.println(roomInfo.getNo());
		model.addAttribute("optionsList", oService.getOptionsList());
		return "booking/form";
	}

	// 추가
	@PostMapping("/add")
	@Transactional(rollbackFor = Exception.class) // 트랜잭션. 예외 발생 시 롤백.
	public String addBooking(BookingVo booking, RedirectAttributes reAttr) {
		log.info("Booking::Add");
		booking.replaceDate();
		System.out.println(booking);

		// 정상적으로 DB에 입력이 되지 않았을 경우.
		// if (bService.addBooking(booking) < 0)

		// DB에 저장
		bService.addBooking(booking);

		// 예약 스테이터스 + booking객체를 리다이렉트어트리뷰트 전용 DTO에 집어넣음.
		BookingReAttr bReAttr = new BookingReAttr("予約", booking);

		// 그걸 전송
		reAttr.addFlashAttribute("reAttr", bReAttr);
		// 매핑 details로 리다이렉트
		return "redirect:details";
		// return "/roomInfo/list";
	}

	// 예약 추가 및 수정 직후 세부 정보 확인
	// @RequestMapping(value = "/details", method = RequestMethod.GET)
	@GetMapping("/details")
	public String getBooking(Model model, HttpServletRequest req) {
		log.info("Booking::details");

		// redirect 시 전달 해준 flashAttr을 객체로 가공
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(req);
		// 전달된 객체를 받아줄 변수
		BookingVo booking = null;
		String stat = null;
		if (inputFlashMap != null) {
			// reAttr의 이름을 가진 요소를 get.
			BookingReAttr reAttr = (BookingReAttr) inputFlashMap.get("reAttr");
			// reAttr 안에서 booking과 stat을 나눔
			System.out.println(reAttr.getBooking());
			booking = reAttr.getBooking();
			// DB에 저장된 날짜를 input:type=date 에 맞춰서 바꿔주는 메소드
			booking.returnDate();

			stat = reAttr.getStat();
			// 모델에 속성 추가
			model.addAttribute("booking", booking);
			model.addAttribute("stat", stat);
		}

		// 예약정보에는 없는 방 정보를 불러오기 위한 속성
		model.addAttribute("roomInfo", rService.getRoomInfo(booking.getRoomNo()));
		return "booking/details";
	}

	// searchForm 이동
	@RequestMapping("/searchName")
	public String searchName() {
		return "booking/searchBookingForName";
	}

	@RequestMapping("/searchNo")
	public String searchNo() {
		return "booking/searchBookingForNo";
	}

	// 예약자 이름과 휴대폰번호로 검색하여 확인
	@PostMapping("/details")
	public String getBooking(String name, String phone, String no, Model model) {
		log.info("Booking :: details");
		// variable Part
		ArrayList<BookingVo> bookingList = new ArrayList<BookingVo>();
		BookingVo booking = new BookingVo();
		int varNo = 0;
		String varName = name;
		String varPhone = phone;

		// Service Part

		if (no != "") {
			varNo = Integer.parseInt(no);
			booking = bService.getBookingByNo(varNo);
			if (booking == null) {
				return "booking/noBooking";
			}
			booking.returnDate();
			model.addAttribute("booking", booking);
			model.addAttribute("roomInfo", rService.getRoomInfo(booking.getRoomNo()));
			return "booking/details";
		} else if (varName != null && varPhone != null) {
			bookingList = bService.getBookingListByName(name, phone);
			for (BookingVo bookingVo : bookingList) {
				bookingVo.returnDate();
			}
			model.addAttribute("bookingList", bookingList);
			model.addAttribute("options", oService.getOptions(Integer.parseInt(booking.getOptions())));
			return "booking/searchList";
		} else {
			return "booking/noBooking";
		}

	}

	// 예약 수정폼으로 이동
	@PostMapping("/mod")
	public String modBookingForm(BookingVo booking, Model model) {
		log.info("mod");
		model.addAttribute("booking", booking);
		model.addAttribute("roomInfo", rService.getRoomInfo(booking.getRoomNo()));
		model.addAttribute("optionsList", oService.getOptionsList());

		// jsp url
		return "booking/mod";
	}

	// 예약 정보 수정 처리
	@PostMapping("/update")
	@Transactional(rollbackFor = Exception.class) // 트랜잭션. 예외 발생 시 롤백.
	public String modBooking(BookingVo booking, RedirectAttributes reAttr) {
		log.info("update");
		booking.replaceDate();
		System.out.println(booking);

		// 정상적으로 DB에 입력이 되지 않았을 경우.
		// if (bService.modBooking(booking) < 0)

		// DB에 저장
		bService.modBooking(booking);

		// 예약 스테이터스 + booking객체를 리다이렉트어트리뷰트 전용 DTO에 집어넣음.
		BookingReAttr bReAttr = new BookingReAttr("修正", booking);

		// 그걸 전송
		reAttr.addFlashAttribute("reAttr", bReAttr);

		// 매핑 details로 리다이렉트
		return "redirect:details";
		// return "/roomInfo/list";
	}

	// 예약 취소
	@PostMapping("/cancel")
	@Transactional(rollbackFor = Exception.class)
	public String cancelBooking(BookingVo booking, RedirectAttributes reAttr) {
		log.info("cancel");
		if (bService.cancelBooking(booking) > 0) {
			reAttr.addFlashAttribute("stat", "成功");
		} else {
			reAttr.addFlashAttribute("stat", "失敗");
		}
		return "redirect:cancelComplete";
	}

	@GetMapping("/cancelComplete")
	public String cancelComplete(Model model) {
		log.info("cancelComplete");
		// 리다이렉트속성에서 stat 받아서 넘겨주기
		String stat = (String) model.asMap().get("stat");
		model.addAttribute("stat", stat);

		// 취소 성공 페이지로
		return "booking/cancelBooking";
	}
}

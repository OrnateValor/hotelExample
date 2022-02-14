package com.ovalor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ovalor.service.BookingService;
import com.ovalor.service.ConsultationService;
import com.ovalor.service.NoticeService;
import com.ovalor.service.OptionsService;
import com.ovalor.service.RoomInfoService;
import com.ovalor.utill.CustomUtill;
import com.ovalor.vo.AdminUser;
import com.ovalor.vo.NoticeVo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/*")
@Log4j
@AllArgsConstructor
public class AdminController {
	private NoticeService nService;
	private BookingService bService;
	private RoomInfoService riService;
	private ConsultationService cService;
	private OptionsService oService;

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = { "/", "/main" })
	public String adminMain(Model model, HttpSession session) {
		log.info("Admin::Main");
		AdminUser admin = CustomUtill.getAdmin(session);
		if (admin != null) {
			return "redirect:menu";
		}
		model.addAttribute("auth");
		return "admin/main";
	}

	// adminMenu - Get = forwarding
	@GetMapping("/menu")
	public String adminMenu(HttpSession session, RedirectAttributes reAttr) {
		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		log.info("auth:" + auth);
		if (!auth) {
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:main";
		}
		return "admin/menu";
	}

	// adminMenu - Post = afterLogin
	@PostMapping("/menu")
	public String adminMenu(String id, String pw, RedirectAttributes reAttr, HttpSession session) {
		log.info("Admin::Menu");
		AdminUser admin;
		boolean auth = CustomUtill.authChk(id, pw);
		log.info("auth:" + auth);
		if (!auth) {
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:main";
		}
		admin = new AdminUser(id, pw);
		session.setAttribute("admin", admin);
		return "redirect:menu";
	}

	// noticeForm
	@PostMapping("/notice")
	public String noticeForm(HttpSession session, RedirectAttributes reAttr) {
		log.info("Admin::NoticeForm");
		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);

			return "redirect:main";
		}
		return "admin/noticeForm";
	}

	// addNotice
	@PostMapping("/addNotice")
	public String addNotice(NoticeVo notice, Model model, HttpSession session) {
		log.info("Admin::Add Notice Process");
		nService.addNotice(notice);
		return "redirect:/notice/list";
	}

	// modNoticeForm
	@GetMapping("/modNotice/{no}")
	public String modNotice(@PathVariable("no") int no, Model model) {
		log.info("Admin::Modify Notice Form");
		model.addAttribute("notice", nService.getNotice(no));
		return "admin/modNoticeForm";
	}

	// modNotice
	@PostMapping("/modNotice")
	public String modNotice(NoticeVo notice, Model model) {
		log.info("Admin::Modify Notice Process");
		int result = nService.modNotice(notice);
		if (result < 1) {
			model.addAttribute("stat", "修正失敗");
			model.addAttribute("notice", notice);
			return "admin/modNoticeForm";
		}
		return "redirect:/notice/list";
	}

	// admin :: booking
	@RequestMapping("/booking")
	public String booking(Model model, RedirectAttributes reAttr, HttpSession session) {
		log.info("Admin::BookingList");
		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:main";
		}
		model.addAttribute("bookingList", bService.getBookingList());
		return "admin/bookingList";
	}

	@GetMapping("bookingDetails")
	public String bookingDetails() {

		return "/booking/details";
	}

	// modPayment
	@PostMapping("/modPayment")
	public String modPayment(String id, RedirectAttributes reAttr) {
		log.info("Admin :: modPayment");
		log.info("targetId : " + id);
		if (bService.modPayment(Integer.parseInt(id)) > 0) {
			reAttr.addFlashAttribute("status", "success");
			return "redirect:/admin/booking";
		}

		reAttr.addFlashAttribute("status", "error");
		return "redirect:/admin/booking";
	}

	// Admin :: roomInfo
	@RequestMapping("/roomInfo")
	public String roomInfo(Model model, RedirectAttributes reAttr, HttpSession session) {
		log.info("Admin :: roomInfo");
		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:main";
		}
		model.addAttribute("roomInfoList", riService.getRoomInfoListAdmin());
		return "/roomInfo/list";
	}

	// Admin :: consultation
	@PostMapping("")
	public String consultation(Model model, RedirectAttributes reAttr, HttpSession session) {
		log.info("Admin :: consultation");
		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:main";
		}
		model.addAttribute("consultationList", cService.getConsultationList());
		return "/consultation/list";
	}

	// Admin :: options
	@RequestMapping("/options")
	public String options(Model model, RedirectAttributes reAttr, HttpSession session) {
		log.info("Admin :: options");
		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:main";
		}

		model.addAttribute("optionsList", oService.getOptionsList());
		return "/options/list";
	}

}

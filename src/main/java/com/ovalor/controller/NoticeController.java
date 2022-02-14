package com.ovalor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ovalor.service.NoticeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/notice/*")
@Log4j
@AllArgsConstructor
public class NoticeController {
	private NoticeService nService;

	// getList
	@GetMapping("/list")
	public String getNoticeList(Model model) {
		log.info("getNoticeList");
		model.addAttribute("noticeList", nService.getNoticeList());

		return "notice/list";
	}

	// select
	@GetMapping("/details/{no}")
	public String getNotice(@PathVariable("no") int no, Model model) {
		log.info("getNoticeDetails");
		model.addAttribute("notice", nService.getNotice(no));

		return "notice/details";
	}
}

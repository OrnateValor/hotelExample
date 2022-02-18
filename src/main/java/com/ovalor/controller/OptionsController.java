package com.ovalor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ovalor.service.OptionsService;
import com.ovalor.utill.CustomUtill;
import com.ovalor.vo.OptionsVo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/options/*")
@AllArgsConstructor
public class OptionsController {
	private OptionsService oService;

	@GetMapping("/details/{no}")
	public String optionsDetails(@PathVariable("no") int no, Model model) {
		log.info("optionsDetails");
		model.addAttribute("options", oService.getOptions(no));

		return "options/details";
	}
	
	@RequestMapping("/inActive")
	public String inActive(OptionsVo options, Model model, RedirectAttributes reAttr, HttpSession session) {
		log.info("inActive options");
		boolean auth = CustomUtill.authChk(session);
		log.info("auth:" + auth);
		if (!auth) {
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:/admin/main";
		}

		int result = oService.inActive(options);
		if (result < 1) {
			reAttr.addFlashAttribute("options", oService.getOptions(options.getNo()));
			return ("redirect:details/" + Integer.toString(options.getNo()));
		}
		return "redirect:/admin/options";
	}

	@RequestMapping("/del")
	public String del(int no, Model model, RedirectAttributes reAttr, HttpSession session) {
		log.info("del options");
		boolean auth = CustomUtill.authChk(session);
		log.info("auth:" + auth);
		if (!auth) {
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:/admin/main";
		}

		int result = oService.del(no);
		if (result < 1) {
			reAttr.addFlashAttribute("options", oService.getOptions(no));
			return ("redirect:details/" + Integer.toString(no));
		}
		return "redirect:/admin/options";
	}
}

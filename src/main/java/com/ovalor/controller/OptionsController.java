package com.ovalor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ovalor.service.OptionsService;

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
}

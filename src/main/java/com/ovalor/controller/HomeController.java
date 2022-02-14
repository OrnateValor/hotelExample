package com.ovalor.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ovalor.service.BookingService;
import com.ovalor.service.ConsultationService;
import com.ovalor.service.NoticeService;
import com.ovalor.service.OptionsService;
import com.ovalor.service.RoomInfoService;
import com.ovalor.service.SliderImagesService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@AllArgsConstructor
public class HomeController {
	private SliderImagesService sService;

	/* Simply selects the home view to render by returning its name. */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Project Activated!!");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("sliderImagesList", sService.getSliderImagesList());
		model.addAttribute("serverTime", formattedDate);

		return "home";
	}
}

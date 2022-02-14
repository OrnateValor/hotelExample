package com.ovalor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ovalor.service.SliderImagesService;
import com.ovalor.utill.CustomUtill;
import com.ovalor.vo.SliderImagesVo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/sliderImages/*")
@Controller
@Log4j
@AllArgsConstructor
public class SliderImagesController {
	private SliderImagesService sService;

	@RequestMapping("/addForm")
	public String addSliderImagesForm(Model model, RedirectAttributes reAttr, HttpSession session) {
		log.info("addSliderImagesForm");
		boolean auth = CustomUtill.authChk(session);
		log.info("auth:" + auth);
		if (!auth) {
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:/admin/main";
		}

		return "sliderImages/addSliderImgForm";
	}

	@PostMapping("/add")
	@Transactional
	public String addSliderImages(SliderImagesVo img,
			MultipartFile file, Model model, RedirectAttributes reAttr,
			HttpSession session) throws Exception {
		log.info("addSliderImagesProc");
		boolean auth = CustomUtill.authChk(session);
		log.info("auth:" + auth);
		if (!auth) {
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:/admin/main";
		}

		String originalFilename = CustomUtill.upFiles(file, session);
		img.setFileName(originalFilename);

		int result = sService.addSliderImages(img);

		// when insert fail
		if (result < 1) {
			reAttr.addFlashAttribute("sliderImages", img);
			return "redirect:/sliderImages/addForm";
		}

		return "redirect:/admin/sliderImages";
	}

	@GetMapping("/mod/{no}")
	public String modSliderImagesForm(int no, Model model, RedirectAttributes reAttr, HttpSession session) {
		log.info("modSliderImages");
		boolean auth = CustomUtill.authChk(session);
		log.info("auth:" + auth);
		if (!auth) {
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:main";
		}

		model.addAttribute("sliderImages", sService.getSliderImages(no));

		return "";
	}
}

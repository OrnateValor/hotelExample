package com.ovalor.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ovalor.service.RoomInfoService;
import com.ovalor.utill.CustomUtill;
import com.ovalor.vo.RoomInfoVo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller // 컨트롤러임을 명시
@RequestMapping("/roomInfo/*") // 해당 uri 일 때 들어옴.
@AllArgsConstructor // 생성자 자동생성.
public class RoomInfoController {
	// 서비스 정의
	private RoomInfoService riService;

	// GetList
	@GetMapping("/list")
	public String getRoomInfoList(Model model, HttpSession session) {
		log.info("getRoomInfoList()");
		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (auth) {
			// RoomList for Admin
			log.info("auth:" + auth);
			model.addAttribute("roomInfoList", riService.getRoomInfoListAdmin());
		} else {
			// RoomList for Customer
			model.addAttribute("roomInfoList", riService.getRoomInfoList());
		}
		// return url
		return "roomInfo/list";
	}

	// Select
	@GetMapping("/details/{no}")
	public String getRoomInfo(@PathVariable("no") int no, Model model) {
		log.info("getRoomInfo");
		model.addAttribute("roomInfo", riService.getRoomInfo(no));
		// return url
		return "roomInfo/details";
	}

	// Add
	@PostMapping("/addForm")
	public String addRoomInfo(Model model, HttpSession session, RedirectAttributes reAttr) {
		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:/admin/main";
		}

		return "roomInfo/addForm";
	}

	@PostMapping("/add")
	@Transactional
	public String add(RoomInfoVo roomInfo, MultipartFile imgs, HttpSession session, RedirectAttributes reAttr)
			throws Exception {
		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:/admin/main";
		}

		/* 이미지 파일 저장 */
		// 저장 경로
		// String uploadPath =
		// "C:\\Users\\1\\eclipse-workspace\\ovalor\\src\\main\\webapp\\resources\\Img"
		String uploadPath = session.getServletContext().getRealPath("/resources/Img/");
		log.info("uploadPath : " + uploadPath);
		File dir = new File(uploadPath);
		log.info("dir Exists : " + dir.exists());

		// 해당 경로가 존재하지 않을 경우, 경로 생성
		if (!dir.exists()) {
			log.info("if");
			dir.mkdirs();
			log.info("after mkdir : " + dir.exists());
		}

		// 원본 파일명 - SHA256으로 컨버트
		String originalFilename = CustomUtill.encryptSha(imgs.getOriginalFilename());
		// String originalFilename = imgs.getOriginalFilename();

		// 저장될 파일 껍데기 - 파일 위치와 파일 이름을 갖고 있음.
		File savFile = new File(uploadPath, originalFilename);

		// 파일 저장 이후 roomInfo 객체에 해당 경로 저장.
		imgs.transferTo(savFile);
		roomInfo.setImages(originalFilename);

		int result = riService.addRoomInfo(roomInfo);

		// when insert fail
		if (result < 1) {
			reAttr.addFlashAttribute("roomInfo", roomInfo);
			return "redirect:/roomInfo/addForm";
		}
		return "redirect:/roomInfo/list";
	}

	// Modify
	@GetMapping("/modify/{no}")
	public String modRoomInfoForm(int no, Model model, HttpSession session, RedirectAttributes reAttr) {
		System.out.println("Admin::modRoomInfoForm");

		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:/admin/main";
		}

		model.addAttribute("roomInfo", riService.getRoomInfo(no));
		// return url
		return "roomInfo/modForm";
	}

	@PostMapping("/mod")
	@Transactional
	public String modRoomInfo(RoomInfoVo roomInfo, MultipartFile imgs, HttpSession session, RedirectAttributes reAttr)
			throws Exception {
		System.out.println("Admin::modRoomInfo");

		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:/admin/main";
		}

		log.info("images : " + roomInfo.getImages());
		if (roomInfo.getImages().equals("")) {
			/* 이미지 파일 저장 */
			// 저장 경로
			// String uploadPath =
			// "C:\\Users\\1\\eclipse-workspace\\ovalor\\src\\main\\webapp\\resources\\Img"
			String uploadPath = session.getServletContext().getRealPath("/resources/Img/");
			log.info("uploadPath : " + uploadPath);
			File dir = new File(uploadPath);
			log.info("dir Exists : " + dir.exists());

			// 해당 경로가 존재하지 않을 경우, 경로 생성
			if (!dir.exists()) {
				log.info("if");
				dir.mkdirs();
				log.info("after mkdir : " + dir.exists());
			}

			// 원본 파일명 - SHA256으로 컨버트
			String originalFilename = CustomUtill.encryptSha(imgs.getOriginalFilename());
			// String originalFilename = imgs.getOriginalFilename();

			// 저장될 파일 껍데기 - 파일 위치와 파일 이름을 갖고 있음.
			File savFile = new File(uploadPath, originalFilename);

			// 파일 저장 이후 roomInfo 객체에 해당 경로 저장.
			imgs.transferTo(savFile);
			roomInfo.setImages(originalFilename);
		}

		int result = riService.modRoomInfo(roomInfo);

		// when update fail
		if (result < 1) {
			reAttr.addFlashAttribute("roomInfo", roomInfo);
			return "redirect:/roomInfo/modForm";
		}

		return "redirect:details/" + roomInfo.getNo();
	}

	@PostMapping("/delete")
	@Transactional
	public String delRoomInfo(int no, String deleteFlag, HttpSession session, RedirectAttributes reAttr) {
		System.out.println("Admin::delRoomInfoForm");

		// auth Chk -> getAdmin from session and ChkAuth
		boolean auth = CustomUtill.authChk(session);
		if (!auth) {
			log.info("auth:" + auth);
			reAttr.addFlashAttribute("auth", auth);
			return "redirect:/admin/main";
		}

		int result = riService.delRoomInfo(no, deleteFlag);

		// when delete fail
		if (result < 1) {
			return "redirect:details/" + no;
		}
		return "redirect:/roomInfo/list";
	}

	@RequestMapping("/uploadImg")
	public String roomImgUpload(MultipartFile images, HttpServletRequest req) {
		log.info("Img Upload : " + images.getOriginalFilename());

		// 이미지를 저장할 경로로 dir 지정.

		String saveDir = req.getSession().getServletContext().getRealPath("/resources/Img");
		File dir = new File(saveDir);

		// 해당 경로가 존재하지 않을 경우, 경로 생성
		if (!dir.exists()) {
			dir.mkdir();
		}

		return "";
	}

}

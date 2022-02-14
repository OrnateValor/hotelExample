package com.ovalor.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ovalor.service.ConsultationService;
import com.ovalor.vo.ConsultationVo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/consultation/*")
@AllArgsConstructor
public class ConsultationController {
	private ConsultationService cService;

	private boolean chkPw(int no, String pw) {
		return cService.getConsultation(no).getPasswd().equals(pw);
	}

	// 목록 불러오기.
	@RequestMapping("/list")
	public String getConsultationList(Model model) {
		log.info("consultation>>>>>>>>>>list");
		// 화면에 뿌려주고자 하는 객체를 모델에 담아줌.
		model.addAttribute("consultationList", cService.getConsultationList());

		return "consultation/list";
	}

	// 넘버로 게시글 Get 하기
	@GetMapping("/details/{no}")
	public String getConsultation(@PathVariable("no") int no, Model model) {
		log.info("consultation>>>>>>>>>>details");

		ConsultationVo consultation = cService.getConsultation(no);

		// 화면에 뿌려주고자 하는 객체를 모델에 담아줌.
		model.addAttribute("consultation", consultation);

		return "consultation/details";
	}

	// 비밀번호 달린 게시물 보기 체크
	@PostMapping("/lockChk")
	public String lockChk(int no, String pw, RedirectAttributes reAttr) {
		log.info("LockChk");

		if (!chkPw(no, pw)) {
			log.info(chkPw(no, pw));
			reAttr.addFlashAttribute("error", "パスワードが正しくありません。");
			return "redirect:/consultation/list";
		}

		return "redirect:/consultation/details/" + no;
	}

	// 등록 폼으로
	@RequestMapping("/add")
	public String addForm() {
		log.info("consultation>>>>>>>>>>form");

		return "consultation/form";
	}

	// 답글, 게시글 등록
	@PostMapping("/add")
	@Transactional(rollbackFor = Exception.class) // 트랜잭션. 예외 발생 시 롤백.
	public String add(ConsultationVo consultation, RedirectAttributes reAttr) {
		log.info("consultation>>>>>>>>>>>add");
		if (consultation.getGrno() != 0) {
			log.info("Add Reply");
			if (consultation.getGrgrod() > cService.cntReply(consultation.getGrno())) {
				cService.upGrgrod(consultation.getGrno(), consultation.getGrgrod());
			}
			cService.addReply(consultation);
		} else {
			log.info("Add Consultation");
			cService.addConsultation(consultation);
		}

		log.info("Add Complete!");
		reAttr.addFlashAttribute("consultation", consultation);
		return "redirect:details";
	}

	// 게시글 등록 후 해당 글 보기
	@GetMapping("/details")
	public String getConsultation(Model model, HttpServletRequest req) {
		log.info("details");

		// redirect 시 전달 해준 flashAttr을 객체로 가공
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(req);
		// 전달된 객체를 받아줄 변수
		ConsultationVo consultation = null;
		if (inputFlashMap != null) {
			// consultation의 이름을 가진 요소를 get.
			consultation = (ConsultationVo) inputFlashMap.get("consultation");
			// 모델에 속성 추가
			model.addAttribute("consultation", consultation);
		}
		return "consultation/details";
	}

	// 게시글 삭제
	@PostMapping("/del")
	@Transactional(rollbackFor = Exception.class)
	public String delConsultation(int no, String pw, RedirectAttributes reAttr) {
		log.info("consultation>>>>>>>>>>>>>>>>del");

		// 비밀번호 체크
		if (!chkPw(no, pw)) {
			log.info(chkPw(no, pw));
			reAttr.addFlashAttribute("error", "パスワードが正しくありません。");
			return "redirect:/consultation/details/" + no;
		} else {
			if (cService.delConsultation(no) < 0) {
				reAttr.addFlashAttribute("stat", "失敗");
			}
			reAttr.addFlashAttribute("stat", "成功");
			return "redirect:delComplete";
		}
	}

	// 삭제처리 성공
	@GetMapping("/delComplete")
	public String delComplete(Model model) {
		log.info("delComplete");
		// 리다이렉트속성에서 stat 받아서 넘겨주기
		String stat = (String) model.asMap().get("stat");
		model.addAttribute("stat", stat);
		return "consultation/delConsultation";
	}

	// 답글 - 등록 폼으로
	@PostMapping("/replyForm")
	public String replyConsultation(int no, int grno, Model model) {

		model.addAttribute("grno", grno);

		model.addAttribute("consultation", cService.getConsultation(no));

		// 작성 전의 댓글 수 +1 = 나열 될 순서.
		model.addAttribute("grgrod", (cService.getConsultation(no).getGrgrod() + 1));
		// no의 depth를 확인하여 +1 해줌. => no로 가져온 게시글이 댓글이 아닐 경우 = 1 = 댓글이 됨.
		// no로 가져온 게시글이 댓글일 경우 = 해당 글의 depth +1 = n번째 댓글.
		model.addAttribute("depth", (cService.getConsultation(no).getDepth() + 1));
		return "consultation/form";
	}
}

package com.ovalor.utill;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ovalor.vo.AdminUser;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class CustomUtill {
	public static AdminUser getAdmin(HttpSession session) {
		AdminUser admin = (AdminUser) session.getAttribute("admin");
		return admin;
	}

	public static boolean authChk(String id, String pw) {
		return (id.equals("OrnateValor") && pw.equals("ov3939"));
	}

	public static boolean authChk(HttpSession session) {
		AdminUser admin = getAdmin(session);
		if (admin == null) {
			return false;
		}
		return (admin.getId().equals("OrnateValor") && admin.getPw().equals("ov3939"));
	}

	// Apache Common Codec 이 있어야만 적용됨.
	public static String encryptSha(String text) throws NoSuchAlgorithmException {
		// SHA256의 미디어다이제스트 로드
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// 파라미터의 String을 바이트화 하여 md에 기록
		md.update(text.getBytes());
		// byte 배열에 저장
		byte data[] = md.digest();
		// 문자열을 저장해 줄 버퍼
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			// 각 바이트를 16진수로 변환하는 작업을 거친 후 버퍼에 저장 - byte to HexString 검색.
			buffer.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
		}
		// Hex 문자열을 담아줄 버퍼
		StringBuffer hexBuffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(0xff & data[i]);
			if (hex.length() == 1) {
				// 길이가 1이면 0을 저장하고
				hexBuffer.append('0');
			}
			// 그 외에는 hex 그대로 다 저장.
			hexBuffer.append(hex);
		}
		// 이렇게 버퍼로 저장된 버퍼를 문자열로 변환하여 반환.
		return hexBuffer.toString();
	}

	public static String upFiles(MultipartFile file, HttpSession session) throws Exception {
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
		String originalFilename = CustomUtill.encryptSha(file.getOriginalFilename());
		// String originalFilename = fileName.getOriginalFilename();

		// 저장될 파일 껍데기 - 파일 위치와 파일 이름을 갖고 있음.
		File savFile = new File(uploadPath, originalFilename);

		// 파일 저장 이후 객체에 해당 경로 저장.
		file.transferTo(savFile);
		return originalFilename;
	}

}

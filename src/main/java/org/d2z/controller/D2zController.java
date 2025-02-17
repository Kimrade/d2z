package org.d2z.controller;

import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginUserDTO;
import org.d2z.service.CompanyUserService;
import org.d2z.service.EngineerUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/d2z")
public class D2zController {
	
	private final EngineerUserService eus;
	private final CompanyUserService cus;
	
	@PreAuthorize("permitAll")
	@GetMapping({"/","/main"})
	public String mainGet() {
		log.info("main get 진입 - main 화면 조회");
		return "/d2z/main";
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/login")
	public void loginGet() {
		log.info("login get 진입 - 로그인 화면 조회");
	}
	
	@GetMapping("/loginError")
	public String loginErrorGet() {
		log.info("/loginError get 진입");
		
		return "redirect:/d2z/login";
	}
	
	@PostMapping("/loginError")
	public String loginErrorPost() {
		log.info("/loginError post 진입");
		
		return "redirect:/d2z/login";
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/member")
	public void membershipGet() {
		log.info("membership get 진입 - 회원가입 화면 조회");
	}
	
	@PostMapping("/member")
	public String membershipPost(@RequestParam("usertype") String type, LoginUserDTO loginUserDTO, CompanyUserDTO companyUserDTO, EngineerUserDTO engineerUserDTO, RedirectAttributes ra) {
		log.info("membership post 진입 - 회원가입 요청 => 아이디, 비밀번호 정보 + 사업체 사용자 혹은 엔지니어 사용자 정보");
		
		log.info("확인용 : "+type);
		
		if(type != null && type.length() > 0) {
			if(type.equals("사업체")) {
				cus.companyUserInfoInsert(loginUserDTO, companyUserDTO);
				ra.addFlashAttribute("memberConfirmAlert", "사업체 사용자 회원가입이 완료되었습니다.");
				log.info("사업체 완료");
			}else {
				eus.engineerUserInfoInsert(loginUserDTO, engineerUserDTO);
				ra.addFlashAttribute("memberConfirmAlert", "엔지니어 사용자 회원가입이 완료되었습니다.");
				log.info("엔지니어 완료");
			}
		}
		
		return "redirect:/d2z/";
	}
	
	

	
	
	
	
	
	
}

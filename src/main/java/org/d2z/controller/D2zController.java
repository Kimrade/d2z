package org.d2z.controller;

import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginDTO;
import org.d2z.repository.LoginRepository;
import org.d2z.service.AdminUserService;
import org.d2z.service.CompanyUserService;
import org.d2z.service.EngineerUserService;
import org.d2z.service.PublicAnnouncementService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/d2z")
public class D2zController {
	
	private final AdminUserService aus;
	private final EngineerUserService eus;
	private final CompanyUserService cus;
	private final PublicAnnouncementService pas;
	private final LoginRepository lr;
	
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
	
	@PostMapping("/login")
	public void loginPost(@RequestParam("username")String id) {
		
	}
	
	@PreAuthorize("permitAll")
	@GetMapping("/member")
	public void membershipGet() {
		log.info("membership get 진입 - 회원가입 화면 조회");
	}
	
	@PostMapping("/member")
	public void membershipPost(LoginDTO loginDTO, CompanyUserDTO companyUserDTO, EngineerUserDTO engineerUserDTO) {
		log.info("membership post 진입 - 회원가입 요청 => 아이디, 비밀번호 정보 + 사업체 사용자 혹은 엔지니어 사용자 정보");
		
	}

	
	
	
	
	
	
}

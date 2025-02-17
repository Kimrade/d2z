package org.d2z.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Log4j2
public class AdminUserController {
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/main")
	public String adminMainGet() {
		log.info("adminMain get 진입 - 관리자 메인 화면 진입");
		
		return "/admin/admin";
	}
	
	
	
	
}

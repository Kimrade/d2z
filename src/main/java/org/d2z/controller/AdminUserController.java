package org.d2z.controller;

import org.d2z.dto.AdminUserDTO;
import org.d2z.service.AdminUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Log4j2
public class AdminUserController {
	
	private final AdminUserService aus;
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AdminUser')")
	@GetMapping("/main")
	public String adminMainGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		log.info("adminMain get 진입 - 관리자 메인 화면 진입");
		
		AdminUserDTO adminUserDTO = aus.findByAdminId(userDetails.getUsername());
		
		model.addAttribute("adminUserDTO", adminUserDTO);
		
		return "/admin/admin";
	}
	
	
	
	
}

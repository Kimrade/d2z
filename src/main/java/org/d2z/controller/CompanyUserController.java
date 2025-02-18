package org.d2z.controller;

import org.d2z.service.CompanyUserService;
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
@Log4j2
@RequestMapping("/company")
public class CompanyUserController {
	
	private final CompanyUserService cus;
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/main")
	public String companyMainGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		log.info("companyMain get 진입 - 회사 유저 메인 진입");
		
		model.addAttribute("companyUserDTO", cus.companyUserInfo(userDetails.getUsername()));
		
		return "/company/company";
	}

}

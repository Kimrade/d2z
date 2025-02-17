package org.d2z.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/company")
public class CompanyUserController {
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_CompanyUser')")
	@GetMapping("/main")
	public String companyMainGet(@AuthenticationPrincipal UserDetails userDetails) {
		log.info("companyMain get 진입 - 회사 유저 메인 진입");
		
		log.info("확인용 : "+userDetails);
		
		log.info(userDetails.getUsername());
		
		return "/company/company";
	}

}

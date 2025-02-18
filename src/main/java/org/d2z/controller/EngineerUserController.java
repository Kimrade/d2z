package org.d2z.controller;

import org.d2z.dto.EngineerUserDTO;
import org.d2z.service.EngineerUserService;
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
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/engineer")
public class EngineerUserController {
	
	private final EngineerUserService eus;

	@PreAuthorize("isAuthenticated() and hasRole('ROLE_EngineerUser')")
	@GetMapping("/main")
	public String engineerGet(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		
		EngineerUserDTO engineerUserDTO = eus.engineerUserInfo(userDetails.getUsername());
		
		model.addAttribute("engineerUserDTO", engineerUserDTO);
		
		return "/engineer/engineer";
	}
	
	
	
}
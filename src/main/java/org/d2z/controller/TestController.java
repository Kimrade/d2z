package org.d2z.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/test")
@Log4j2
@RequiredArgsConstructor
public class TestController {
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/")
	public String mainGet() {
		log.info("main get 진입");
		return "main";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/main")
	public void mainGet2() {
		log.info("main get 진입");
	}
	
	@GetMapping("/login")
	public void loginGet() {
		log.info("login get 진입");
	}
	
	@PostMapping("/login")
	public String loginPost() {
		log.info("login post 진입");
		
		return "redirect:/test/";
		
	}
}
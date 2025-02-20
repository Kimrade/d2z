package org.d2z.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
	
	@GetMapping("/login")
	public void loginGet() {
		log.info("로그인 get 요청 --------");
		//String error, String logout
//		log.info("logout : "+logout);
//		
//		if(logout != null) {
//			log.info("사용자 로그아웃 ---------------");
//		}
		
		log.info("Remember-Me authentication successful: " + SecurityContextHolder.getContext().getAuthentication());
	}
	
	
	
}

package org.d2z.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/engineer")
public class EngineerUserController {

	
	@GetMapping("/main")
	public String engineerGet() {
		
		return "/engineer/engineer";
	}
	
	
	
}

//package org.d2z.controller;
//
//import org.d2z.service.ContactService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//
//@RestController
//@Log4j2
//@RequiredArgsConstructor
//public class ContactController {
//	
//	private final ContactService cs;
//	
//	@PostMapping("/mail/contact")
//	public ResponseEntity<String> sencContact(@RequestParam String name, @RequestParam String message, @RequestParam String subject, @RequestParam String email){
//		
//		cs.sendContactEmail(email, subject, message, name);
//		
//		return ResponseEntity.ok("메세지가 발송되었습니다. 감사합니다.");
//	}
//	
//	
//	
//}

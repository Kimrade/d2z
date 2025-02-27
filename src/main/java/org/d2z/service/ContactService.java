//package org.d2z.service;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//
//@Service
//@Log4j2
//@RequiredArgsConstructor
//public class ContactService {
//	
//	private final JavaMailSender jms;
//	
//	public void sendContactEmail(String from, String subject, String message, String name) {
//		SimpleMailMessage smm = new SimpleMailMessage();
//		
//		smm.setTo("sungjun960@naver.com");
//		smm.setSubject(subject);
//		smm.setText("From : "+from +"\n\n"+message+"\n\n"+name);
//		
//		jms.send(smm);
//	}
//	
//}

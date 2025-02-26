package org.d2z.controller;

import org.d2z.dto.ChatRoomDTO;
import org.d2z.service.ChatService;
import org.d2z.service.CompanyUserService;
import org.d2z.service.EngineerUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/chat")
@Controller
public class ChatViewController {
	
	private final ChatService cs;
	private final CompanyUserService cus;
	private final EngineerUserService eus;
	
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_CompanyUser', 'ROLE_EngineerUser')")
	@GetMapping("/room")
	public String chatRoomGet(@RequestParam("roomNo") Long roomNo, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        ChatRoomDTO chatRoom = cs.readOneChatRoom(roomNo);
        if (chatRoom == null) {
        	if(cus.companyUserInfo(userDetails.getUsername()) != null) {
        		return "redirect:/company/main";
        	}else {
        		return "redirect:/engineer/main";
        	}
        }
        
        if(cus.companyUserInfo(userDetails.getUsername()) != null) {
        	model.addAttribute("loginUserDTO", cus.companyUserInfo(userDetails.getUsername()));
        	model.addAttribute("sender", cus.companyUserInfo(userDetails.getUsername()).getCompanyUserName());
        	model.addAttribute("receiver", eus.engineerUserInfo(cs.readOneChatRoom(roomNo).getEngineerUserId()).getEngineerUserName());
        }else {
        	model.addAttribute("loginUserDTO", eus.engineerUserInfo(userDetails.getUsername()));
        	model.addAttribute("sender", eus.engineerUserInfo(userDetails.getUsername()).getEngineerUserName());
        	model.addAttribute("receiver", cus.companyUserInfo(cs.readOneChatRoom(roomNo).getCompanyUserId()).getCompanyUserName());
        }
        
        model.addAttribute("roomNo", roomNo);
        model.addAttribute("chatRoom", chatRoom);
        
        return "chat/chatRoom";
    }
	
	
	
	
}

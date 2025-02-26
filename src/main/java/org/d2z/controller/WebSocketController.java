package org.d2z.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.d2z.dto.ChatMessageDTO;
import org.d2z.service.ChatService;

@Controller
@Log4j2
@RequiredArgsConstructor
public class WebSocketController {
	
	private final ChatService cs;
	
    @MessageMapping("/chat/{roomNo}")  // 클라이언트에서 "/app/chat/{roomNo}"로 보낸 요청을 처리
    @SendTo("/topic/chat/{roomNo}")  // 모든 구독자에게 메시지 전송
    public ChatMessageDTO sendMessage(@PathVariable String roomNo, ChatMessageDTO message) {
        System.out.println("메시지 전송됨: " + message);
        
        ChatMessageDTO chatMessageDTO = cs.chatOtherAndSaveRecord(message);
        
        return chatMessageDTO;
    }
}

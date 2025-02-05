package org.d2z.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.d2z.dto.ChatMessageDTO;
import org.d2z.service.ChatMessageService;
import org.d2z.service.ChatRoomService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequiredArgsConstructor
@Log4j2
public class WebSocketController {
	
	private final ChatRoomService crs;
	private final ChatMessageService cms;
	
	@MessageMapping("/chat/{roomId}")
	@SendTo("/sub/{roomId}")
	public ChatMessageDTO sendMessage(@DestinationVariable()String roomId, ChatMessageDTO message) {
		
		message.builder().roomId(roomId).createdDate(LocalDateTime.now());
		cms.save(message);
		return message;
	}
	
	
	@GetMapping("/messages/{roomId}")
	@ResponseBody
	public List<ChatMessageDTO> getMessages(@PathVariable() String roomId) {
		return cms.listMessage(roomId);
	}
	
	@PostMapping("/createRoom")
	@ResponseBody
	public String createRoom(@RequestParam() String engineerUserId, @RequestParam() String companyUserId) {
		
		return crs.createRoom(engineerUserId, companyUserId);
	}
	

}

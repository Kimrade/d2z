package org.d2z.controller;

import java.util.List;

import org.d2z.dto.ChatMessageDTO;
import org.d2z.dto.ChatRoomDTO;
import org.d2z.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/chat/api")
public class ChatController {
	
	private final ChatService cs;
	
	// 채팅 보내기 - api 처리
	@PostMapping("/message")
	public ChatMessageDTO sendMessage(@RequestBody ChatMessageDTO chatMessageDTO){
		
		return cs.chatOtherAndSaveRecord(chatMessageDTO);
	}
	
	// 채팅방 기록 가져오기
	@GetMapping("/message")
	public ResponseEntity<?> getRecords(@RequestParam(value = "roomNo", required = false) Long roomNo) {
	    if (roomNo == null) {
	        return ResponseEntity.badRequest().body("roomNo 값이 필요합니다.");
	    }
	    
	    List<ChatMessageDTO> messages = cs.listChatRecords(roomNo);
	    return ResponseEntity.ok(messages);
	}
	
	// 채팅방 생성 하기
	@PostMapping("/room")
	public ResponseEntity<Long> createRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
		Long roomNo = cs.makeChatRoom(chatRoomDTO.getEngineerUserId(), chatRoomDTO.getCompanyUserId());
		return ResponseEntity.ok(roomNo);
	}
	
	
	// 채팅방의 값을 가져오는것
	@GetMapping("/room/{roomNo}")
	public ChatRoomDTO readChatRoom(@PathVariable("roomNo") Long roomNo) {
		return cs.readOneChatRoom(roomNo);
	}
	
	// 채팅방 정보 조회 -> 대화 상대방과 당사자의 id를 중심으로 사용할 예정
	@GetMapping("/room/{engineerUserId}/{companyUserId}")
	public ChatRoomDTO searchChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
		
		return cs.findByEngineerUserIdAndCompanyUserId(chatRoomDTO.getEngineerUserId(), chatRoomDTO.getCompanyUserId());
	}
}

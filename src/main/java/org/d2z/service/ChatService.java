package org.d2z.service;

import java.util.List;

import org.d2z.dto.ChatMessageDTO;
import org.d2z.dto.ChatRoomDTO;

public interface ChatService {
	
	// 채팅방 생성
	public Long makeChatRoom(String engineerUserId, String companyUserId);
	
	// 채팅방 조회
	public ChatRoomDTO readOneChatRoom(Long roomNo);
	
	// 채팅방 조회 (유저 2명을 통해서)
	public ChatRoomDTO findByEngineerUserIdAndCompanyUserId(String engineerUserId, String companyUserId);
	
	// 채팅방 조회 (유저 1명을 통해서)
	public List<ChatRoomDTO> findChatRoomById(String id);
	
	// 채팅 보내기, 기록 저장
	public ChatMessageDTO chatOtherAndSaveRecord(ChatMessageDTO chatMessageDTO);
	
	// 채팅 이전 기록 읽어오기
	public List<ChatMessageDTO> listChatRecords(Long roomNo);
	
	// 채팅방에서 제일 최근 시간의 메세지 불러오기
	public ChatMessageDTO lastMessageByRoom(Long roomNo);
	
	
}
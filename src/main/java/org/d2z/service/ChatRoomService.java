package org.d2z.service;

import org.d2z.dto.ChatRoomDTO;

public interface ChatRoomService {
	
	public ChatRoomDTO viewChattingRoom(String roomId);
	
	public String createRoom(String EngineerUserId, String CompanyUserId);
	
	public String generateRoomId(String EngineerUserId, String CompanyUserId);
	
}

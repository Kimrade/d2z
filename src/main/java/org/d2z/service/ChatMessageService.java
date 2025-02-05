package org.d2z.service;

import java.util.List;

import org.d2z.dto.ChatMessageDTO;

public interface ChatMessageService {
	
	public ChatMessageDTO findById(Long id);
	
	public Long save(ChatMessageDTO chatMessageDTO);
	
	public void delete(Long id);
	
	public List<ChatMessageDTO> listMessage(String roomId);
}

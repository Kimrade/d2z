package org.d2z.service;

import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.ChatMessage;
import org.d2z.domain.ChatRoom;
import org.d2z.dto.ChatMessageDTO;
import org.d2z.dto.ChatRoomDTO;
import org.d2z.repository.ChatMessageRepository;
import org.d2z.repository.ChatRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
	
	private final ChatRoomRepository crr;
	private final ChatMessageRepository cmr;
	private final ModelMapper modelMapper;
	
	
	@Override
	public boolean makeChatRoom(String engineerUserId, String companyUserId) {
		
		boolean result = false;
		
		if(crr.findByEngineerUserIdAndCompanyUserId(engineerUserId, companyUserId).isEmpty()) {
			crr.save(ChatRoom.builder().engineerUserId(engineerUserId).companyUserId(companyUserId).build());
			result=true;
		}
		
		return result;
	}
	
	@Override
	public ChatRoomDTO readOneChatRoom(Long roomNo) {
		return modelMapper.map(crr.findById(roomNo).orElseThrow(), ChatRoomDTO.class);
	}
	
	@Override
	public ChatRoomDTO findByEngineerUserIdAndCompanyUserId(String engineerUserId, String companyUserId) {
		
		return modelMapper.map(crr.findByEngineerUserIdAndCompanyUserId(engineerUserId, companyUserId).orElseThrow(), ChatRoomDTO.class);
	}
	
	@Override
	public ChatMessageDTO chatOtherAndSaveRecord(ChatMessageDTO chatMessageDTO) {
		
		ChatMessage chatMessage = cmr.save(modelMapper.map(chatMessageDTO, ChatMessage.class));
		
		ChatMessageDTO result = ChatMessageDTO.builder().messageNo(chatMessage.getMessageNo()).roomNo(chatMessage.getChatRoom().getRoomNo())
														.createdTime(chatMessage.getCreatedTime()).sender(chatMessage.getSender())
														.messageContent(chatMessage.getMessageContent()).build();
		
		return result;
	}
	
	@Override
	public List<ChatMessageDTO> listChatRecords(Long roomNo) {
		
		return cmr.findByChatRoomRoomNoOrderByCreatedTimeAsc(roomNo).stream().map(x -> modelMapper.map(x, ChatMessageDTO.class)).collect(Collectors.toList());
	}
	
	
}

package org.d2z.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.ChatMessage;
import org.d2z.domain.ChatRoom;
import org.d2z.dto.ChatMessageDTO;
import org.d2z.dto.ChatRoomDTO;
import org.d2z.repository.ChatMessageRepository;
import org.d2z.repository.ChatRoomRepository;
import org.d2z.repository.CompanyUserRepository;
import org.d2z.repository.EngineerUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
	
	private final ChatRoomRepository crr;
	private final ChatMessageRepository cmr;
	private final CompanyUserRepository cur;
	private final EngineerUserRepository eur;
	private final ModelMapper modelMapper;
	
	
	@Override
	@Transactional
	public Long makeChatRoom(String engineerUserId, String companyUserId) {
		
		Long roomNo = -1L;
		
		if(crr.findByEngineerUserIdAndCompanyUserId(engineerUserId, companyUserId).isEmpty()) {
			ChatRoom chatRoom = crr.save(ChatRoom.builder().engineerUserId(engineerUserId).companyUserId(companyUserId).build());
		
			roomNo = chatRoom.getRoomNo();
		}
		
		return roomNo;
	}
	
	@Override
	public ChatRoomDTO readOneChatRoom(Long roomNo) {
		return modelMapper.map(crr.findById(roomNo).orElseThrow(), ChatRoomDTO.class);
	}
	
	@Override
	public ChatRoomDTO findByEngineerUserIdAndCompanyUserId(String engineerUserId, String companyUserId) {
		
		if(crr.findByEngineerUserIdAndCompanyUserId(engineerUserId, companyUserId).isEmpty()) {
			return null;
		}else {
			return modelMapper.map(crr.findByEngineerUserIdAndCompanyUserId(engineerUserId, companyUserId).orElseThrow(), ChatRoomDTO.class);
		}
	}
	
	@Override
	@Transactional
	public ChatMessageDTO chatOtherAndSaveRecord(ChatMessageDTO chatMessageDTO) {
		
		ChatRoom chatRoom = crr.findById(chatMessageDTO.getRoomNo()).orElseThrow();
		
		ChatMessage chatMessage = ChatMessage.builder().chatRoom(chatRoom).messageContent(chatMessageDTO.getMessageContent()).sender(chatMessageDTO.getSender()).build();
		
		cmr.save(chatMessage);
		
		ChatMessageDTO result = ChatMessageDTO.builder().messageNo(chatMessage.getMessageNo())
												.roomNo(chatMessage.getChatRoom().getRoomNo())
												.createdTime(chatMessage.getCreatedTime())
												.sender(chatMessage.getSender())
												.messageContent(chatMessage.getMessageContent())
												.build();
		
		return result;
	}
	
	@Override
	public List<ChatMessageDTO> listChatRecords(Long roomNo) {
		
		return cmr.findByChatRoomRoomNoOrderByCreatedTimeAsc(roomNo).stream().map(x -> modelMapper.map(x, ChatMessageDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<ChatRoomDTO> findChatRoomById(String id) {
		
		List<ChatRoomDTO> dtolist = null;
		
		List<ChatRoom> chatRoomList = null;
		
		if(cur.findByLoginId(id).isPresent()) {
			chatRoomList = crr.findByCompanyUserId(id);
		}
		
		if(eur.findByLoginId(id).isPresent()) {
			chatRoomList = crr.findByEngineerUserId(id);
		}
		
		if(chatRoomList != null) {
			dtolist = chatRoomList.stream().map(x -> modelMapper.map(x, ChatRoomDTO.class)).collect(Collectors.toList());
		}
		
		return dtolist;
	}

	@Override
	public ChatMessageDTO lastMessageByRoom(Long roomNo) {
		
		if(cmr.findTopByChatRoom_RoomNoOrderByCreatedTimeDesc(roomNo).isPresent()) {
			
			ChatMessage lastMessage = cmr.findTopByChatRoom_RoomNoOrderByCreatedTimeDesc(roomNo).get();
			
			ChatMessageDTO result = ChatMessageDTO.builder().sender(lastMessage.getSender())
													.roomNo(roomNo)
													.messageNo(lastMessage.getMessageNo())
													.messageContent(lastMessage.getMessageContent())
													.createdTime(lastMessage.getCreatedTime()).build();
			
			return result;
			
		}else {
			return null;
		}
	}
	
	
}

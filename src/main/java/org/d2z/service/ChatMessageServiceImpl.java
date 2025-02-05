package org.d2z.service;

import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.ChatMessage;
import org.d2z.domain.ChatRoom;
import org.d2z.dto.ChatMessageRequestDTO;
import org.d2z.dto.ChatMessageDTO;
import org.d2z.repository.ChatMessageRepository;
import org.d2z.repository.ChatRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatMessageServiceImpl implements ChatMessageService {
	
	private final ChatMessageRepository cmr;
	private final ModelMapper modelMapper;
	
	@Override
	public ChatMessageDTO findById(Long id) {
		
		return modelMapper.map(cmr.findById(id).orElseThrow(), ChatMessageDTO.class);
	}

	@Override
	public void delete(Long id) {
		
		cmr.delete(modelMapper.map(ChatMessageDTO.builder().id(id).build(), ChatMessage.class));
	}

	@Override
	public Long save(ChatMessageDTO chatMessageDTO) {
		
		ChatMessage cm = cmr.save(modelMapper.map(ChatMessageDTO.builder().message(chatMessageDTO.getMessage()).sender(chatMessageDTO.getSender()).roomId(chatMessageDTO.getRoomId()).build(),ChatMessage.class));
		
		return cm.getId();
	}

	@Override
	public List<ChatMessageDTO> listMessage(String roomId) {
		
		return cmr.findByroomIdOrderByCreatedDateAsc(roomId).stream().map(x -> modelMapper.map(x, ChatMessageDTO.class)).collect(Collectors.toList());
	}

}

package org.d2z.service;

import java.util.ArrayList;
import java.util.List;

import org.d2z.domain.PostMessage;
import org.d2z.domain.PostMessageUser;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PostMessageDTO;
import org.d2z.dto.PostMessageUserDTO;
import org.d2z.repository.PostMessageRepository;
import org.d2z.repository.PostMessageUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostMessageServiceImpl implements PostMessageService {
	
	private final PostMessageUserRepository pmur;
	private final PostMessageRepository pmr;
	private final ModelMapper modelMapper;
	
	@Override
	@Transactional
	public PostMessageDTO writeMessage(PostMessageDTO postMessageDTO) {
		
		PostMessageUserDTO receiver = modelMapper.map(pmur.findById(postMessageDTO.getReceiverId()),PostMessageUserDTO.class);
		PostMessageUserDTO sender = modelMapper.map(pmur.findById(postMessageDTO.getSenderId()),PostMessageUserDTO.class);
		
		PostMessage message = PostMessage.builder()
									.content(postMessageDTO.getContent())
									.receiver(modelMapper.map(receiver,PostMessageUser.class))
									.sender(modelMapper.map(sender,PostMessageUser.class))
									.title(postMessageDTO.getTitle())
									.build();
		pmr.save(message);
				
		return PostMessageDTO.toDTO(message);
	}

	@Override
	@Transactional
	public List<PostMessageDTO> sentMessage(String id, PageRequestDTO pageRequestDTO) {
		
		Page<PostMessage> result = pmr.findbySenderId(id, pageRequestDTO.getPageable(""));
		List<PostMessage> list = result.getContent();
		
		List<PostMessageDTO> dtolist = new ArrayList<>();
		
		for(PostMessage message : list) {
			if(!message.isDeletedBySender()) {
				dtolist.add(PostMessageDTO.toDTO(message));
			}
		}
		
		return dtolist;
	}

	@Override
	@Transactional
	public List<PostMessageDTO> receivedMessage(String id, PageRequestDTO pageRequestDTO) {
		
		Page<PostMessage> result = pmr.findbyReceiverId(id, pageRequestDTO.getPageable(""));
		List<PostMessage> list = result.getContent();
		
		List<PostMessageDTO> dtolist = new ArrayList<>();
		
		for(PostMessage message : list) {
			if(!message.isDeletedByReceiver()) {
				dtolist.add(PostMessageDTO.toDTO(message));
			}
		}
		
		return dtolist;
	}

	@Override
	@Transactional
	public boolean deleteByReceivedPart(int no, PostMessageUserDTO userDTO) {
		
		boolean result = false;
		
		PostMessage message = pmr.findById(no).orElseThrow();
		
		PostMessageUser user = modelMapper.map(userDTO, PostMessageUser.class);
		
		if(message.getReceiver() == user) {
			pmr.deleteById(no);
			if(message.isDeleted()) {
				pmr.delete(message);
			}
			result = true;
		}
		return result;
	}

	@Override
	@Transactional
	public boolean deleteBySentPart(int no, PostMessageUserDTO userDTO) {
		
		boolean result = false;
		
		PostMessage message = pmr.findById(no).orElseThrow();
		
		PostMessageUser user = modelMapper.map(userDTO, PostMessageUser.class);
		
		if(message.getSender() == user) {
			pmr.deleteById(no);
			if(message.isDeleted()) {
				pmr.delete(message);
			}
			result = true;
		}
		return result;
	}
}
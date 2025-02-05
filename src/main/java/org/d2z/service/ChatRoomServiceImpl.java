package org.d2z.service;

import org.d2z.domain.ChatRoom;
import org.d2z.dto.ChatRoomRequestDTO;
import org.d2z.dto.ChatRoomDTO;
import org.d2z.repository.ChatRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

	private final ChatRoomRepository crr;
	private final ModelMapper modelMapper;

	@Override
	public ChatRoomDTO viewChattingRoom(String roomId) {
		
		return modelMapper.map(crr.findById(roomId).orElseThrow(), ChatRoomDTO.class);
		
	}

	@Override
	public String createRoom(String EngineerUserId, String CompanyUserId) {
		
		String roomId = generateRoomId(EngineerUserId, CompanyUserId);
		if(!crr.existsById(roomId)) {
			crr.save(ChatRoom.builder().roomId(roomId).build());
		}
		
		return null;
	}

	@Override
	public String generateRoomId(String engineerUserId, String companyUserId) {
		return engineerUserId.compareTo(companyUserId) < 0 ? engineerUserId + "-" + companyUserId : companyUserId + "-" + engineerUserId ;
	}

}

package org.d2z.repository;

import java.util.List;
import java.util.Optional;

import org.d2z.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	// 채팅 이전 기록 읽어오기
	public List<ChatMessage> findByChatRoomRoomNoOrderByCreatedTimeAsc(Long roomNo);
	
	// 채팅방에서 가장 늦은 시간의 채팅 시간 가져오기
	Optional<ChatMessage> findTopByChatRoom_RoomNoOrderByCreatedTimeDesc(Long chatRoomNo);
	
}
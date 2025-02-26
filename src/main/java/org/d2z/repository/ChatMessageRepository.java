package org.d2z.repository;

import java.util.List;

import org.d2z.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

	// 채팅 이전 기록 읽어오기
	public List<ChatMessage> findByChatRoomRoomNoOrderByCreatedTimeAsc(Long roomNo);
		
}
package org.d2z.repository;

import java.util.List;

import org.d2z.domain.ChatMessage;
import org.d2z.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
	
	
	List<ChatMessage> findByroomIdOrderByCreatedDateAsc(String roomId);
	
}

package org.d2z.repository;

import java.util.Optional;

import org.d2z.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
	Optional<ChatRoom> findById(Long id);
}

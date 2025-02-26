package org.d2z.repository;

import java.util.List;
import java.util.Optional;

import org.d2z.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	
	// 채팅방 조회 (유저를 통해)
	public Optional<ChatRoom> findByEngineerUserIdAndCompanyUserId(String engineerUserId, String companyUserId);
	
	// 채팅방 조회
	public List<ChatRoom> findByCompanyUserId(String companyUserId);
	
	public List<ChatRoom> findByEngineerUserId(String engineerUserId);
}
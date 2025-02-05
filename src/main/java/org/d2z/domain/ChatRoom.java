package org.d2z.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.d2z.dto.ChatRoomRequestDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
	
	// 채팅방 번호
	@Id
	private String roomId;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;

}
package org.d2z.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder(toBuilder = true)
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageNo;
	
	private String sender;
	private String messageContent;
	
	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdTime;
	
	@ManyToOne
	@JoinColumn(name = "chat_room_room_no")
	private ChatRoom chatRoom;
	
	
	public ChatMessage withChatRoom(ChatRoom chatRoom) {
        return this.toBuilder()
                .chatRoom(chatRoom)
                .build();
    }
	
	
	
}

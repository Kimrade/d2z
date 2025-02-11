package org.d2z.dto;

import java.time.LocalDateTime;

import org.d2z.domain.PostMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMessageDTO {
	
	private String title;
	
	private String content;
	
	private String senderId;
	
	private String receiverId;
	
	private LocalDateTime createdTime;
	
	
	public static PostMessageDTO toDTO(PostMessage postMessage) {
		return new PostMessageDTO(
				postMessage.getTitle(),
				postMessage.getContent(),
				postMessage.getSender().getId(),
				postMessage.getReceiver().getId(),
				postMessage.getCreatedTime()
				);
	}

}

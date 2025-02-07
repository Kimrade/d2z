package org.d2z.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
	
	private Long messageNo;
	
	private String sender;
	
	private String messageContent;
	
	private LocalDateTime createdTime;
	
	private Long roomNo;
	
	
	
}

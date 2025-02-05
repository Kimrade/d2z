package org.d2z.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
	
	private Long id;
	private String sender;
	private String message;
	private LocalDateTime createdDate;
	
	private String roomId;
	
}

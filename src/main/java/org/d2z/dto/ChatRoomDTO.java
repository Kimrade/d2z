package org.d2z.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDTO {
	
	private Long roomNo;
	
	private String engineerUserId;
	
	private String companyUserId;

	
}
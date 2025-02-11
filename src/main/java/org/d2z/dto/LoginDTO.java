package org.d2z.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	
	private int userNo;
	
	private int userDiv;
	
	private String id;
	
	private String pw;
	
	
}

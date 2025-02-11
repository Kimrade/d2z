package org.d2z.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserDTO {
	
	private int adminUserNo;
	
	private String adminUserName;
	
	private String adminUserTel;
	
	private int userNo;
	
	private String id;
	
	private int userDiv;
	
	private String pw;
	
}

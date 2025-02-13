package org.d2z.dto;

import org.d2z.domain.MemberRole;

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
	
	private MemberRole userDiv;
	
	private String pw;
	
}

package org.d2z.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserDTO {
	
	// 관리자 고유 번호
	private int adminUserNo;
	
	// 관리자 id
	private String adminUserId;
	
	// 관리자 pw
	private String adminUserPw;
	
	// 관리자 이름
	private String adminUserName;
	
	// 관리자 연락처
	private String adminUserTel;
	
}

package org.d2z.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {
	
	// 관리 사용자 고유 번호 - 어토인크리먼트
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="adminUserNo")
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
package org.d2z.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder(toBuilder = true)
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {
	
	// 관리 사용자 고유 번호 - 어토인크리먼트
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminUserNo;
	
	// 관리자 이름
	@Column(nullable = true)
	private String adminUserName;
	
	// 관리자 연락처
	@Column(nullable = true)
	private String adminUserTel;
	
	// 로그인 정보 - 조인 테이블
	@OneToOne
	@JoinColumn(name = "login_user_no", nullable = false)
	private Login login;
}
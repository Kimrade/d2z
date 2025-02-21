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
public class EngineerUser {
	
	// 엔지니어 사용자 전용 번호 - 어토인크리먼트
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int engineerUserNo;
	
	// 엔지니어 사용자 주소
	@Column(nullable = false)
	private String engineerUserAdd;
	
	@Column(nullable = false)
	private String engineerUserName;
	
	// 엔지니어 사용자 전화번호
	@Column(nullable = false)
	private String engineerUserTel;
	
	// 엔지니어 사용자 경력
	@Column(nullable = false)
	private double engineerUserCareer;
	
	// 엔지니어 사용자 주요 회사
	@Column(nullable = true)
	private String engineerUserMajorCompany;
	
	// 엔지니어 사용자 직급
	@Column(nullable = true)
	private String engineerUserPosition;
	
	// 엔지니어 사용자 직업군
	@Column(nullable = false)
	private String engineerUserJob;
	
	// 엔지니어 이메일 주소
	@Column(nullable = false)
	private String engineerUserEmail;
	
	// 엔지니어 비고
	@Column(nullable = true , length = 2000)
	private String engineerUserNote;
	
	// 엔지니어 프로필 소개 내용
	@Column(nullable = true , length = 2000)
	private String engineerUserInfo;
	
	@Column(nullable = false)
	private String engineerUserBirth;
	
	// 아이디 삭제 유무
	@Builder.Default
	private int isDeleted=0;
	
	// 아이디 승인 유무
	@Builder.Default
	private int isApproved=0;
	
	@OneToOne
	@JoinColumn(name = "login_user_no", nullable = false)
	private Login login;
	
	
	
}

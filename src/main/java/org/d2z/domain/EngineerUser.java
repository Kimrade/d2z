package org.d2z.domain;

import org.hibernate.annotations.ColumnDefault;

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
public class EngineerUser {
	
	// 엔지니어 사용자 전용 번호 - 어토인크리먼트
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int engineerUserNo;
	
	// 엔지니어 사용자 id
	@Column(nullable = false, unique=true)
	private String engineerUserId;
	
	// 엔지니어 사용자 pw
	@Column(nullable = false)
	private String engineerUserPw;
	
	// 엔지니어 사용자 생년월일
	@Column(nullable = false)
	private String engineerUserBirth;
	
	// 엔지니어 사용자 주소
	@Column(nullable = false)
	private String engineerUserAdd;
	
	// 엔지니어 사용자 전화번호
	@Column(nullable = false)
	private String engineerUserTel;
	
	// 엔지니어 사용자 경력
	@Column(nullable = false)
	private int engineerUserCareer;
	
	// 엔지니어 사용자 주요 회사
	@Column(nullable = false)
	private String engineerUserMajorCompany;
	
	// 엔지니어 사용자 직급
	@Column(nullable = false)
	private String engineerUserPosition;
	
	// 엔지니어 사용자 직업군
	@Column(nullable = false)
	private String engineerUserJob;
	
	@ColumnDefault("0")
	private int isDeleted;
	
}

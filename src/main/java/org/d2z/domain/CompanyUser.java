package org.d2z.domain;

import org.hibernate.annotations.ColumnDefault;

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
@ToString(exclude = "login")
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUser {
	
	// 사업주 사용자 이메일
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyUserNo;
	
	@Column(nullable = false)
	private String companyUserEmail;
	
	// 사업주 사업자등록번호
	@Column(nullable = false)
	private String companyNo;
	
	// 사업주 업체명
	@Column(nullable = false)
	private String companyName;
	
	// 사업주 사업장주소
	@Column(nullable = false)
	private String companyAdd;
	
	// 사업주 사업장 전화번호
	@Column(nullable = true)
	private String companyTel;
	
	// 사업주 사용자 팩스번호
	@Column(nullable = true)
	private String companyUserFax;
	
	// 사업주 업체 웹사이트
	@Column(nullable = true)
	private String companySiteAdd;
	
	// 사업주 사용자명 (담당자명)
	@Column(nullable = false)
	private String companyUserName;
	
	// 사업주 사용자 연락처(담당자 연락처)
	@Column(nullable = false)
	private String companyUserTel;
	
	// 사업주 비고
	@Column(nullable = true , length = 2000)
	private String companyNote;
	
	// 사업주 프로필 내용
	@Column(nullable = true , length = 2000)
	private String companyInfo;
	
	// 아이디 삭제 유무
	@ColumnDefault("0")
	private int isDeleted;
	
	// 아이디 승인 유무
	@ColumnDefault("0")
	private int isApproved;
	
	@OneToOne(orphanRemoval = true)
	@JoinColumn(name = "login_user_no", nullable = false)
	private Login login;
}

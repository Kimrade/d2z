package org.d2z.service;

import org.d2z.dto.CompanyUserDTO;

public interface CompanyUserService {
	
	// 사업주 사용자 등록(회원가입)
	public boolean companyUserInfoInsert(CompanyUserDTO companyUserDTO);
	
	// 사업주 사용자 회원정보 수정
	public boolean companyUserInfoModify(CompanyUserDTO companyUserDTO);
	
	// 사업주 사용자 회원정보 삭제
	public boolean companyUserInfoDelete(int companyUserNo);
	
	// 사업주 사용자 정보 조회 (로그인, 프로필, 사업주 정보 상세조회)
	public CompanyUserDTO companyUserInfo(int companyUserNo);
	
	// 사업주 사용자 조회 (사업주 정보 조회) - 페이지 처리
	
}

package org.d2z.service;

import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.LoginDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;

public interface CompanyUserService {
	
	// 사업주 사용자 등록(회원가입)
	public boolean companyUserInfoInsert(LoginDTO loginDTO, CompanyUserDTO companyUserDTO);
	
	// 사업주 사용자 회원정보 수정
	public boolean companyUserInfoModify(LoginDTO loginDTO, CompanyUserDTO companyUserDTO);
	
	// 사업주 사용자 회원정보 완전 삭제(관리자 삭제)
	public boolean companyUserInfoDelete(String id);
	
	// 사업주 사용자 회원정보 삭제 처리(db 남김)
	public boolean companyUserInfoCheckDeleted(String id);
	
	// 사업주 사용자 정보 조회 (로그인, 프로필, 사업주 정보 상세조회)
	public CompanyUserDTO companyUserInfo(String id);
	
	// 사업주 사용자 조회 (사업주 정보 조회) - 페이지 처리
	public PageResponseDTO<CompanyUserDTO> companyUserSearchByKeyword(PageRequestDTO pageRequestDTO);
	
}
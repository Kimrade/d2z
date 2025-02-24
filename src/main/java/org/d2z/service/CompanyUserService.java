package org.d2z.service;

import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.LoginUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;

public interface CompanyUserService {
	
	// 사업주 사용자 등록(회원가입)
	public boolean companyUserInfoInsert(LoginUserDTO loginUserDTO, CompanyUserDTO companyUserDTO);
	
	// 사업주 사용자 회원정보 수정
	public boolean companyUserInfoModify(LoginUserDTO loginUserDTO, CompanyUserDTO companyUserDTO);
	
	// 사업주 사용자 회원정보 완전 삭제(관리자 삭제)
	public boolean companyUserInfoDelete(String id);
	
	// 사업주 사용자 회원정보 삭제 처리(db 남김)
	public boolean companyUserInfoCheckDeleted(String id);
	
	// 사업주 사용자 정보 조회 (로그인, 프로필, 사업주 정보 상세조회)
	public CompanyUserDTO companyUserInfo(String id);
	
	// 사업주 사용자 조회 (사업주 정보 조회) - 페이지 처리
	public PageResponseDTO<CompanyUserDTO> companyUserSearchByKeyword(PageRequestDTO pageRequestDTO);
	
	// 인증받은 사업주 사용자 수
	public int totalCompanyUserCount();
	
	// 등록된 이메일주소로 id 찾기
	public String findCompanyUserByEmail(String companyUserEmail);
	
	// 등록된 담당자 연락처로 id 찾기
	public String findCompanyUserByTelNo(String companyUserTel);
	
	// 사업자 정보 조회 - 고유 번호
	public CompanyUserDTO getCompanyUserInfoByNo(int companyUserNo);
}
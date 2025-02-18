package org.d2z.service;

import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;

public interface EngineerUserService {
	
	// 엔지니어 정보 등록(회원가입)
	public boolean engineerUserInfoInsert(LoginUserDTO loginUserDTO, EngineerUserDTO engineerUserDTO);
	
	// 엔지니어 정보 수정
	public boolean engineerUserInfoModify(LoginUserDTO loginUserDTO, EngineerUserDTO engineerUserDTO);
	
	// 엔지니어 정보 완전 삭제(관리자 삭제)
	public boolean engineerUserInfoDelete(String id);
	
	// 엔지니어 사용자 회원정보 삭제 처리(db 남김)
	public boolean engineerUserInfoCheckDeleted(String id);
	
	// 엔지니어 정보 조회(로그인, 프로필 조회, 엔지니어 상세조회)
	public EngineerUserDTO engineerUserInfo(String id);
	
	// 엔지니어 리스트 정보 조회 (엔지니어 조회) - 페이지 처리
	public PageResponseDTO<EngineerUserDTO> engineerUserSearchByKeyword(PageRequestDTO pageRequestDTO);
	
	// 인증받은 엔지니어 수 조회
	public int totalEngineerCount();
	
	// 등록된 엔지니어 이메일로 id 찾기
	public String findEngineerUserByEmail(String engineerUserEmail);
	
	// 등록된 엔지니어 전화번호로 id 찾기
	public String findEngineerUserByTelNo(String engineerUserTel);
}
package org.d2z.service;

import org.d2z.dto.EngineerUserDTO;

public interface EngineerUserService {
	
	// 엔지니어 정보 등록(회원가입)
	public boolean engineerUserInfoInsert(EngineerUserDTO engineerUserDTO);
	
	// 엔지니어 정보 수정
	public boolean engineerUserInfoModify(EngineerUserDTO engineerUserDTO);
	
	// 엔지니어 정보 삭제
	public boolean engineerUserInfoDelete(int engineerUserNo);
	
	// 엔지니어 정보 조회(로그인, 프로필 조회, 엔지니어 상세조회)
	public EngineerUserDTO engineerUserInfo(int engineerUserNo);
	
	// 엔지니어 리스트 정보 조회 (엔지니어 조회) - 페이지 처리
}

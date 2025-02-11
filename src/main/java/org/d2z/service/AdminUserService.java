package org.d2z.service;

import org.d2z.dto.AdminUserDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginDTO;
import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;

public interface AdminUserService {
	
	// 관리자의 엔지니어, 사업주, 관리자 사용자 id 삭제
	public boolean deleteUser(String id);
	
	// 관리자의 엔지니어, 사업주, 관리자 사용자 id 승인 허가
	public int approveUser(String id);
	
	// 관리자의 엔지니어, 사업주, 관리자 사용자 id 승인 보류
	public int pendingUser(String id);
	
	// 관리자의 엔지니어 사용자 id 승인 x
	public int disApprovedUser(String id);
	
	// 관리자의 사업주 미숭인 사용자 조회 (검색 및 페이지 처리)
	public PageResponseDTO<CompanyUserDTO> searchDisApprovedCompanyUserByKeyword(PageRequestDTO pageRequestDTO);
	
	// 관리자의 사업주 보류 사용자 조회 (검색 및 페이지 처리)
	public PageResponseDTO<CompanyUserDTO> searchPendingCompanyUserByKeyword(PageRequestDTO pageRequestDTO);
	
	// 관리자의 엔지니어 미승인 사용자 조회 (검색 및 페이지 처리)
	public PageResponseDTO<EngineerUserDTO> searchDisApprovedEngineerUserByKeyword(PageRequestDTO pageRequestDTO);
	
	// 관리자의 엔지니어 보류 사용자 조회 (검색 및 페이지 처리)
	public PageResponseDTO<EngineerUserDTO> searchPendingEngineerUserByKeyword(PageRequestDTO pageRequestDTO);
	
	// 관리자의 게시글 삭제
	public boolean deletePublicAnnouncement(int announcementNo);
	
	// 관리자 정보 등록
	public boolean insertAdminUser(LoginDTO loginDTO, AdminUserDTO adminUserDTO);
	
	// 관리자 정보 수정
	public boolean modifyAdminUser(LoginDTO loginDTO, AdminUserDTO adminUserDTO);
}
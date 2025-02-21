package org.d2z.service;

import java.util.List;

import org.d2z.dto.AdminUserDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginUserDTO;
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
	
	// 관리자의 사업주 사용자 탈퇴신청 조회
	public List<EngineerUserDTO> listByEngineerUserDeleted();
	
	// 관리자의 엔지니어 사용자 탈퇴 신청 조회
	public List<CompanyUserDTO> listByCompanyUserDeleted();
	
	// 관리자의 게시글 삭제
	public boolean deletePublicAnnouncement(int announcementNo);
	
	// 관리자 정보 등록
	public boolean insertAdminUser(LoginUserDTO loginUserDTO, AdminUserDTO adminUserDTO);
	
	// 관리자 정보 수정
	public boolean modifyAdminUser(LoginUserDTO loginUserDTO, AdminUserDTO adminUserDTO);
	
	// 관리자 정보 조회
	public AdminUserDTO findByAdminId(String id);
	
	// 대량의 사업체 사용자 탈퇴 처리
	public boolean deleteAllCompanyUserById(List<Integer> companyUserNo);
	
	// 대량의 엔지니어 사용자 탈퇴 처리
	public boolean deleteAllEngineerUserById(List<Integer> engineerUserNo);
	
}
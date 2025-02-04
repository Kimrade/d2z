package org.d2z.service;

import org.d2z.dto.AdminUserDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.PublicAnnouncementDTO;

public interface AdminUserService {
	
	// 관리자의 엔지니어 사용자 id 삭제
	public boolean deleteEngineerUser(EngineerUserDTO engineerUserDTO);
	
	// 관리자의 사업주 사용자 id 삭제
	public boolean deleteCompanyUser(CompanyUserDTO companyUserDTO);
	
	// 관리자의 엔지니어 사용자 id 승인 허가
	public int approveEngineerUser(EngineerUserDTO engineerUserDTO);
	
	// 관리자의 사업주 사용자 id 승인 허가
	public int approveCompanyUser(CompanyUserDTO companyUserDTO);
	
	// 관리자의 엔지니어 사용자 id 승인 보류
	public int pendingEngineerUser(EngineerUserDTO engineerUserDTO);
	
	// 관리자의 사업주 사용자 id 승인 보류
	public int pendingCompanyUser(CompanyUserDTO companyUserDTO);
	
	// 관리자의 엔지니어 사용자 id 승인 x
	public int disApprovedEngineerUser(EngineerUserDTO engineerUserDTO);
	
	// 관리자의 사업주 사용자 id 승인 x
	public int disApprovedCompanyUser(CompanyUserDTO companyUserDTO);
	
	// 관리자의 게시글 삭제
	public boolean deletePublicAnnouncement(PublicAnnouncementDTO publicAnnouncementDTO);
	
	// 관리자 정보 등록
	public boolean insertAdminUser(AdminUserDTO adminUserDTO);
	
	// 관리자 정보 수정
	public boolean modifyAdminUser(AdminUserDTO adminUserDTO);
	
	// 관리자 정보 삭제
	public boolean deleteAdminUser(int adminUserNo);
}
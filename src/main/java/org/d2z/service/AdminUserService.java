package org.d2z.service;

import org.d2z.dto.AdminUserDTO;
import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.PublicAnnouncementDTO;

public interface AdminUserService {
	
	// 관리자의 엔지니어 사용자 id 삭제
	public boolean deleteEngineerUser(int engineerUserNo);
	
	// 관리자의 사업주 사용자 id 삭제
	public boolean deleteCompanyUser(int companyUserNo);
	
	// 관리자의 엔지니어 사용자 id 승인 허가
	public int approveEngineerUser(int engineerUserNo);
	
	// 관리자의 사업주 사용자 id 승인 허가
	public int approveCompanyUser(int companyUserNo);
	
	// 관리자의 엔지니어 사용자 id 승인 보류
	public int pendingEngineerUser(int engineerUserNo);
	
	// 관리자의 사업주 사용자 id 승인 보류
	public int pendingCompanyUser(int companyUserNo);
	
	// 관리자의 엔지니어 사용자 id 승인 x
	public int disApprovedEngineerUser(int engineerUserNo);
	
	// 관리자의 사업주 사용자 id 승인 x
	public int disApprovedCompanyUser(int companyUserNo);
	
	// 관리자의 게시글 삭제
	public boolean deletePublicAnnouncement(int announcementNo);
	
	// 관리자 정보 등록
	public boolean insertAdminUser(AdminUserDTO adminUserDTO);
	
	// 관리자 정보 수정
	public boolean modifyAdminUser(AdminUserDTO adminUserDTO);
	
	// 관리자 정보 삭제
	public boolean deleteAdminUser(int adminUserNo);
}
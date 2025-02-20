package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.CompanyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyUserSearch {
	
	// 사업주 사용자 조회 - 페이지 처리 및 검색 조건 (승인된 사람만)
	public Page<CompanyUser> companyUserSearchByKeyword(String[] types, String keyword, Pageable pageable);
	
	// 사업주 사용자 조회 - 승인되지 않은 사람
	public Page<CompanyUser> companyUserDisapprovecSearchByKeyword(String[] types, String keyword, Pageable pageable);
	
	// 사업주 사용자 조회 - 보류상태로 되어 있는 사람
	public Page<CompanyUser> companyUserPendingSearchByKeyword(String[] types, String keyword, Pageable pageable);
	
	// 사업주 사용자 조회 - 탈퇴 처리되어있는 사람
	public List<CompanyUser> companyUserDeletedList();
	
	// 총 활동중인 사업체 수
	public int totalCompanyCount();
	
}

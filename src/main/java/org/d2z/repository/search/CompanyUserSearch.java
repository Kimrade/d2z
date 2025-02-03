package org.d2z.repository.search;

import org.d2z.domain.CompanyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyUserSearch {
	
	// 사업주 사용자 조회 - 페이지 처리 및 검색 조건
	public Page<CompanyUser> companyUserSearchByKeyword(String[] types, String keyword, Pageable pageable);
	
	
}

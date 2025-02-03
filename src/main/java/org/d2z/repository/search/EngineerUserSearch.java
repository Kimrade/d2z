package org.d2z.repository.search;

import org.d2z.domain.EngineerUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EngineerUserSearch {
	
	// 엔지니어 사용자 조회 - 페이지 처리 및 검색 조건
	public Page<EngineerUser> EngineerUserSearchByKeyword(String[] types, String keyword, Pageable pageable);
	
	//
	
	
	//
	
	
	
}

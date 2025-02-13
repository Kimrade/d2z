package org.d2z.repository.search;

import org.d2z.domain.EngineerUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EngineerUserSearch {
	
	// 엔지니어 사용자 조회 - 페이지 처리 및 검색 조건(승인된 사람만)
	public Page<EngineerUser> EngineerUserSearchByKeyword(String[] types, String keyword, Pageable pageable);
	
	// 엔지니어 사용자 조회 - 승인되지 않은 사람
	public Page<EngineerUser> EngineerUserDisapprovedSearchByKeyword(String[] types, String keyword, Pageable pageable);
	
	// 엔지니어 사용자 조회 - 보류상태로 되어 있는 사람
	public Page<EngineerUser> EngineerUserPendingSearchByKeyword(String[] types, String keyword, Pageable pageable);
	
	
	// 엔지니어 매칭을 위한 조회 서비스
	public Page<EngineerUser> matchingEngineerUserSystem(String[] types, String keyword, Pageable pageable);
	
	// 엔지니어 매칭 조회의 인원수 측정
	public int matchingTotalEngineer(String[] types, String keyword, Pageable pageable);

	
}

package org.d2z.repository.search;

import org.d2z.domain.PublicAnnouncement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublicAnnouncementSearch {
	
	// 공고 게시글의 검색, 조회 (페이지 처리)
	public Page<PublicAnnouncement> publicAnnouncementSearchByKeyword(String[] types, String keyword, Pageable pageable);
	
}

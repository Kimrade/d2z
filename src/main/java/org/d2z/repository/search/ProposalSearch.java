package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.Proposal;

public interface ProposalSearch {
	
	// 제안서 조회 - (리스트 값으로 각 공고문 번호에 따라)
	public List<Proposal> findByAnnouncementNo(int announcementNo);
}

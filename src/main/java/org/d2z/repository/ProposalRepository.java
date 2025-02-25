package org.d2z.repository;

import java.util.Optional;

import org.d2z.domain.Proposal;
import org.d2z.repository.search.ProposalSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalRepository extends JpaRepository<Proposal, Integer>, ProposalSearch {
	
	Optional<Proposal> findByEngineerUser_EngineerUserNoAndPublicAnnouncement_AnnouncementNo(int engineerUserNo, int announcementNo);
	
	
}

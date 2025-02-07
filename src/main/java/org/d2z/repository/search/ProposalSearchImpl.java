package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.Proposal;
import org.d2z.domain.QProposal;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;

public class ProposalSearchImpl extends QuerydslRepositorySupport implements ProposalSearch {

	public ProposalSearchImpl() {
		super(Proposal.class);
	}

	@Override
	public List<Proposal> findByAnnouncementNo(int announcementNo) {
		
		QProposal proposal = QProposal.proposal;
		
		JPQLQuery<Proposal> query = from(proposal);
		
		query.where(proposal.publicAnnouncement.announcementNo.eq(announcementNo));
		
		List<Proposal> list = query.fetch();
		
		return list;
	}

}

package org.d2z.repository;

import org.d2z.domain.EngineerUser;
import org.d2z.domain.Proposal;
import org.d2z.domain.PublicAnnouncement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProposalRepositoryTests {
	
	@Autowired
	private ProposalRepository pr;
	
//	@Test
//	public void saveTest() {
//		pr.save(Proposal.builder()
//				.engineerUser(EngineerUser.builder().engineerUserNo(5).build())
//				.publicAnnouncement(PublicAnnouncement.builder().announcementNo(5).build()).build());
//	}
//	
}

package org.d2z.service;

import org.d2z.dto.ProposalDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProposalServiceTests {
	
	@Autowired
	private ProposalService ps;
	
//	@Test
//	public void sendProposalTest() {
//		ps.sendProposal(ProposalDTO.builder().announcementNo(7)
//				.engineerUserNo(7).build());
//		
//	}
//	
//	@Test
//	public void deleteTest() {
//		log.info("확인용 결과 : "+ps.deleteProposal(1));
//	}
//	
//	@Test
//	public void listProposalTest() {
//		ps.listProposalByAnnouncementNo(4).forEach(x -> log.info("확인용 : "+x));
//	}
	
	
	
}

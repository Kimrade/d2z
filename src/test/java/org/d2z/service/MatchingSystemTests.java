package org.d2z.service;

import org.d2z.dto.MatchingRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MatchingSystemTests {
	
	@Autowired
	private MatchingService ms;
	
//	@Test
//	public void randomRecTest() {
//		
//		MatchingRequestDTO mr = MatchingRequestDTO.builder().build();
//		
//		ms.recommendationEngineerMatching(mr).getDtolist().forEach(x -> log.info("확인용 : "+x));
//	}
//	
//	@Test
//	public void randomRecCountTest() {
//		MatchingRequestDTO mr = MatchingRequestDTO.builder().build();
//		
//		log.info("확인용 : "+ms.recommendationEngineerCount(mr));
//	}
	
}

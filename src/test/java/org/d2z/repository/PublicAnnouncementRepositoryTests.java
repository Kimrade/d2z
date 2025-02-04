package org.d2z.repository;

import org.d2z.domain.PublicAnnouncement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class PublicAnnouncementRepositoryTests {
	
	@Autowired
	private PublicAnnouncementRepository par;
	
//	@Test
//	public void insertTest() {
//		par.save(PublicAnnouncement.builder().announcementName("---- 공고 명").announcementDescription("~~~~~~내용")
//				.serviceAdd("강원도 춘천시 땡땡ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇ").serviceJob("주조").serviceDiv("방문").servicePeriod("6개월").build());
//	}
//	
//	@Test
//	public void manyInsertTest() {
//		for(int i=0; i<100 ; i++) {
//			par.save(PublicAnnouncement.builder().announcementName("---- 공고 명"+i).announcementDescription("~~~~~~내용"+i)
//					.serviceAdd("강원도 춘천시 "+i).serviceJob("주조"+i).serviceDiv("방문"+i).servicePeriod("6개월"+i).build());
//		}
//	}
//	
//	@Test
//	public void deleteTest() {
//		par.deleteById(2);
//	}
//	
//	@Test
//	public void modifyTest() {
//		par.save(PublicAnnouncement.builder().announcementNo(3).announcementName("---- 공고 명").announcementDescription("~~~~~~내용")
//				.serviceAdd("강원도 춘천시 ").serviceJob("주조").serviceDiv("방문").servicePeriod("6개월").build());
//	}
//	
//	@Test
//	public void searchByKeyword() {
//		Pageable pageable = PageRequest.of(0, 10);
//		
//		par.publicAnnouncementSearchByKeyword(null, null, pageable).getContent().forEach(x -> log.info("확인용 : "+x));;
//	}
//	
//	@Test
//	public void readOneTest() {
//		log.info("확인용 : "+par.findById(3).orElseThrow());
//	}
//	
}

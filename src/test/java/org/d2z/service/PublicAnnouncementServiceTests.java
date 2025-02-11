package org.d2z.service;

import java.time.LocalDateTime;

import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PublicAnnouncementDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class PublicAnnouncementServiceTests {
	
	@Autowired
	private PublicAnnouncementService pas;
	
//	@Test
//	public void insertTest() {
//		pas.publicAnnouncementInsert(PublicAnnouncementDTO.builder()
//				.announcementName("그냥 적당히 긴 공고명이 있으면 좋겠asdaㅁㄴㅇ다.")
//				.announcementDescription("아주아주아주 긴 설명이 될거asd같아서 ㅁㄴㅇ2000자 까지해놨는데 어찌될랑가")
//				.serviceAdd("강원도의 주소가 긴 어딘가가 있었으면 좋겠다asd.ㅁㄴㅇ")
//				.serviceJob("가공ㅁasdㄴㅇ")
//				.serviceDiv("방ㅁㄴasdㅇ문")
//				.servicePeriod("2개월asdㅁㄴㅇ")
//				.serviceCost(0)
//				.serviceTotalCost(0)
//				.ServicePersonnel(1)
//				.companyUserNo(2)
//				.build());
//	}
//	
//	@Test
//	public void manyInsertTest() {
//		for(int i=0;i<100;i++) {
//			pas.publicAnnouncementInsert(PublicAnnouncementDTO.builder().announcementName("공고명"+i)
//							.announcementDescription("공고내용"+i).serviceAdd("강원도의 주소"+i).serviceJob("가공"+i).serviceDiv("방문"+i).servicePeriod("2개월"+i)
//							.serviceCost(i).serviceTotalCost(i).ServicePersonnel(i).build());
//		}
//	}
//	
//	@Test
//	public void modifyTest() {
//		pas.publicAnnouncementModify(PublicAnnouncementDTO.builder()
//				.announcementNo(3)
//				.announcementName("면 좋겠다.")
//				.announcementDescription("어찌될랑가")
//				.serviceAdd("강원도의 ")
//				.serviceJob("가공21")
//				.serviceDiv("방문3123")
//				.servicePeriod("2개월12")
//				.serviceCost(0)
//				.serviceTotalCost(0)
//				.ServicePersonnel(100)
//				.build());
//	}
//	
//	@Test
//	public void deleteTest() {
//		pas.publicAnnouncementDelete(4);
//	}
//	
//	@Test
//	public void readOneTest() {
//		log.info("확인용 : "+pas.publicAnnouncementReadOne(4));
//	}
//	
//	@Test
//	public void searchTest() {
//		pas.publicAnnouncementSearchByKeyword(PageRequestDTO.builder().keyword("").build()).getDtolist().forEach(x-> log.info("결과 : "+x));
//	}
	
	
}

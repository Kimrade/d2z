package org.d2z.service;

import org.d2z.dto.CompanyUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CompanyUserServiceTests {

	@Autowired
	private CompanyUserService cus;
	
//	@Test
//	public void insertTest() {
//		cus.companyUserInfoInsert(CompanyUserDTO.builder().companyUserId("aaa").companyUserPw("bbb").companyNo("111-22-33333")
//				.companyName("아주 정말 길고도 긴 회사 이름").companyAdd("아주 정말 길었으면 하는 회사 주소").companyTel("012-345-6789")
//				.companyUserFax("012-3456-7894").companyUserEmail("asdawqeqeqweqwe@gmail.com").companyUserSiteAdd("www.asdasdasdad.com")
//				.companyUserName("홍길동친구").companyUserTel("010-12347-74567").build());
//	}
//	
//	@Test
//	public void manyInsertTest() {
//		for(int i=0;i<100;i++) {
//			cus.companyUserInfoInsert(CompanyUserDTO.builder().companyUserId("a"+i).companyUserPw("b"+i).companyNo("111-22-333"+i)
//			.companyName("아주 정말 길고도 긴 회사 이름"+i).companyAdd("아주 정말 길었으면 하는 회사 주소"+i).companyTel("012-345-678"+i)
//			.companyUserFax("012-3456-789"+i).companyUserEmail("asdawqeqeqweqwe@gmail.com"+i).companyUserSiteAdd("www.asdasdasdad.com"+i)
//			.companyUserName("홍길동친구"+i).companyUserTel("010-12347-74567"+i).build());
//		}
//	}
//	
//	@Test
//	public void modifyTest() {
//		cus.companyUserInfoModify(CompanyUserDTO.builder().companyUserNo(3).companyUserId("a1").companyUserPw("bbb").companyNo("111-22-33333")
//				.companyName("아주 정말 길고도 긴 회사 이름").companyAdd("아주 정말 길었으면 하는 회사 주소").companyTel("012-345-6789")
//				.companyUserFax("012-3456-7894").companyUserEmail("asdawqeqeqweqwe@gmail.com").companyUserSiteAdd("www.asdasdasdad.com")
//				.companyUserName("홍길동친구").companyUserTel("010-12347-74567").build());
//	}
//	
//	@Test
//	public void approvedTest() {
//		cus.companyUserInfoModify(CompanyUserDTO.builder().companyUserNo(99).companyUserId("a97").companyUserPw("bbb").companyNo("111-22-33333")
//				.companyName("아주 정말 길고도 긴 회사 이름").companyAdd("아주 정말 길었으면 하는 회사 주소").companyTel("012-345-6789")
//				.companyUserFax("012-3456-7894").companyUserEmail("asdawqeqeqweqwe@gmail.com").companyUserSiteAdd("www.asdasdasdad.com")
//				.companyUserName("홍길동친구").companyUserTel("010-12347-74567").isApproved(1).build());
//	}
//	
//	@Test
//	public void deleteTest() {
//		cus.companyUserInfoDelete(3);
//	}
//	
//	@Test
//	public void deletedTest() {
//		cus.companyUserInfoCheckDeleted(100);
//	}
//	
//	@Test
//	public void readOneTest() {
//		log.info("확인용 : "+cus.companyUserInfo(100));
//	}
//	
//	@Test
//	public void searchTest() {
//		cus.companyUserSearchByKeyword(PageRequestDTO.builder().keyword("").build()).getDtolist().forEach(x -> log.info("확인용 : "+x));;
//	}
	
	
}

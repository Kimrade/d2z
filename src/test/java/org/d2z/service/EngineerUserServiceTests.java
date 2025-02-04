package org.d2z.service;

import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class EngineerUserServiceTests {
	
	@Autowired
	private EngineerUserService eus;
	
//	@Test
//	public void infoInsertTest() {
//		eus.engineerUserInfoInsert(EngineerUserDTO.builder().engineerUserId("aaa").engineerUserPw("bbb")
//				.engineerUserBirth("19600903").engineerUserAdd("춘천시 동두천길 앙주시").engineerUserTel("010-1234-4567")
//				.engineerUserCareer(2.5).engineerUserMajorCompany("1111111산업").engineerUserPosition("xlawkda팀장")
//				.engineerUserJob("주조").build());
//	}
//	
//	@Test
//	public void infoManyInsertTest() {
//		for(int i=0;i<100;i++) {
//			eus.engineerUserInfoInsert(EngineerUserDTO.builder().engineerUserId("a"+i).engineerUserPw("b"+i)
//					.engineerUserBirth("196009"+i).engineerUserAdd("춘천시 동두천길 앙주시 "+i).engineerUserTel("010-1234-45"+i)
//					.engineerUserCareer(i).engineerUserMajorCompany("132412산업"+i).engineerUserPosition("xlawkda팀장"+i)
//					.engineerUserJob("주조"+i).build());
//		}
//	}
//	
//	@Test
//	public void infoModifyTest() {
//		eus.engineerUserInfoModify(EngineerUserDTO.builder().engineerUserNo(2).engineerUserId("a").engineerUserPw("b")
//				.engineerUserBirth("196009").engineerUserAdd("춘천시 동두천길 앙주시 ").engineerUserTel("010-1234-45")
//				.engineerUserCareer(2).engineerUserMajorCompany("132412산업").engineerUserPosition("xlawkda팀장")
//				.engineerUserJob("주조").build());
//	}
//	
//	@Test
//	public void infoIsApprovedTest() {
//		eus.engineerUserInfoModify(EngineerUserDTO.builder().engineerUserNo(100).engineerUserId("a98").engineerUserPw("b")
//				.engineerUserBirth("196009").engineerUserAdd("춘천시 동두천길 앙주시 ").engineerUserTel("010-1234-45")
//				.engineerUserCareer(2).engineerUserMajorCompany("132412산업").engineerUserPosition("xlawkda팀장")
//				.engineerUserJob("주조").isApproved(1).build());
//	}
//	
//	@Test 
//	public void infoDeleteTest() {
//		log.info( "결과 : "+eus.engineerUserInfoDelete(2));
//	}
//	
//	@Test
//	public void infoCheckDeletedTest() {
//		log.info(eus.engineerUserInfoCheckDeleted(3));
//	}
//	
//	@Test
//	public void infoReadOne() {
//		log.info("결과 : "+eus.engineerUserInfo(3));
//	}
//	
//	@Test
//	public void searchListTest() {
//		eus.engineerUserSearchByKeyword(PageRequestDTO.builder().keyword("").build()).getDtolist().forEach(x ->log.info("확인용 : "+x));
//	}
	
}

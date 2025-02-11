package org.d2z.service;

import org.d2z.dto.EngineerUserDTO;
import org.d2z.dto.LoginDTO;
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
//		
//		LoginDTO login = LoginDTO.builder().id("aaa").pw("bbb").userDiv(1).build();
//		
//		EngineerUserDTO engineerUser = EngineerUserDTO.builder().engineerUserAdd("춘천시 동두천길 앙주시").engineerUserTel("010-1234-4567")
//		.engineerUserCareer(2.5).engineerUserMajorCompany("1111111산업").engineerUserPosition("xlawkda팀장")
//		.engineerUserJob("주조").engineerUserEmail("a@naver.com").build();
//		
//		
//		eus.engineerUserInfoInsert(login, engineerUser);
//	}
//	
//	@Test
//	public void infoManyInsertTest() {
//		for(int i=0;i<100;i++) {
//			
//			LoginDTO login = LoginDTO.builder().id("a"+i).pw("b"+i).userDiv(1).build();
//			
//			EngineerUserDTO engineerUser = EngineerUserDTO.builder().engineerUserAdd("춘천시 동두천길 앙주시"+i).engineerUserTel("010-1234-4567"+i)
//			.engineerUserCareer(2.5+i).engineerUserMajorCompany("1111111산업"+i).engineerUserPosition("xlawkda팀장"+i)
//			.engineerUserJob("주조"+i).engineerUserEmail("a"+i+"@naver.com").build();
//			
//			
//			eus.engineerUserInfoInsert(login, engineerUser);
//			
//		}
//	}
//	
//	@Test
//	public void infoModifyTest() {
//		LoginDTO login = LoginDTO.builder()
//				.id("a2")
//				.pw("bccdef")
//				.build();
//		
//		EngineerUserDTO engineerUser = EngineerUserDTO.builder()
//				.engineerUserNo(4)
//				.engineerUserAdd("춘천시 동두천길 앙주시asd123")
//				.engineerUserTel("010-1234-4567213")
//				.engineerUserCareer(2.5)
//				.engineerUserMajorCompany("1111111333산업")
//				.engineerUserPosition("xlawkdasdaq12a팀장")
//				.engineerUserJob("주23조")
//				.engineerUserEmail("a4444@naver.com")
//				.build();
//		
//		eus.engineerUserInfoModify(login, engineerUser);
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
//		log.info( "결과 : "+eus.engineerUserInfoDelete("a2"));
//	}
//	
//	@Test
//	public void infoCheckDeletedTest() {
//		log.info(eus.engineerUserInfoCheckDeleted("a3"));
//	}
//	
//	@Test
//	public void infoReadOne() {
//		log.info("결과 : "+eus.engineerUserInfo("a4"));
//	}
//	
//	@Test
//	public void searchListTest() {
//		eus.engineerUserSearchByKeyword(PageRequestDTO.builder().keyword("").build()).getDtolist().forEach(x ->log.info("확인용 : "+x));
//	}
	
}

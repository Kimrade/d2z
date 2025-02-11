package org.d2z.service;

import org.d2z.dto.AdminUserDTO;
import org.d2z.dto.LoginDTO;
import org.d2z.dto.PageRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AdminUserServiceTests {

	@Autowired
	private AdminUserService aus;
	
//	@Test
//	public void approveTest() {
//		aus.approveUser("a0");
//	}
//
//	@Test
//	public void pendingTest() {
//		aus.pendingUser("a0");
//	}
//	
//	@Test
//	public void disapproveTest() {
//		aus.disApprovedUser("a0");
//	}
//	
//	
//	@Test
//	public void deletePublicTest() {
//		aus.deletePublicAnnouncement(5);
//	}
//	
//	@Test
//	public void insertAdminUser() {
//		
//		LoginDTO login = LoginDTO.builder().id("admin2123").pw("1234").userDiv(0).build();
//		
//		AdminUserDTO admin1 = AdminUserDTO.builder().adminUserName("홍길동ㅁㄴㅇㅁㄴㅇ")
//							.adminUserTel("010-1234-5678")
//							.build();
//		
//		
//		aus.insertAdminUser(login,admin1);
//		
//	}
//	
//	@Test
//	public void modifyAdminTest() {
//		
//		LoginDTO login = LoginDTO.builder()
//							.id("admin1")
//							.pw("9012")
//							.build();
//				
//		AdminUserDTO admin = AdminUserDTO.builder()
//							.adminUserName("홍길동23")
//							.adminUserTel("001011545481241")
//							.build();
//		
//		aus.modifyAdminUser(login,admin);
//	}
//	
//	@Test
//	public void deleteAdminTest() {
//		aus.deleteUser("admin2123");
//	}
//	
//	@Test
//	public void searchCompanyUserDisapprvedTest() {
//		aus.searchDisApprovedCompanyUserByKeyword(PageRequestDTO.builder().keyword("").build()).getDtolist().forEach(x -> log.info("확인용 : "+x));
//	}
//	
//	@Test
//	public void searchCompanyUserPendingTest() {
//		aus.searchPendingCompanyUserByKeyword(PageRequestDTO.builder().build()).getDtolist().forEach(x -> log.info("확인용 : "+x));
//	}
//	
//	@Test
//	public void searchEngineerUserDisapprovedTest() {
//		aus.searchDisApprovedEngineerUserByKeyword(PageRequestDTO.builder().build()).getDtolist().forEach(x -> log.info("확인용 : "+x));
//	}
//	
//	@Test
//	public void searchEngineerUserPendingTest() {
//		aus.searchPendingEngineerUserByKeyword(PageRequestDTO.builder().build()).getDtolist().forEach(x -> log.info("확인용 : "+x));
//	}
}

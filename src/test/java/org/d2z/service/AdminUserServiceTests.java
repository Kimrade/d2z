package org.d2z.service;

import org.d2z.dto.AdminUserDTO;
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
	
	@Test
	public void deleteEngineerUserTest() {
		aus.deleteEngineerUser(2);
	}
	
	@Test
	public void deleteCompanyUser() {
		aus.deleteCompanyUser(3);
	}
	
	@Test
	public void approveEngineerTest() {
		aus.approveEngineerUser(3);
	}
	
	@Test
	public void approveCompanyTest() {
		aus.approveCompanyUser(2);
	}
	
	@Test
	public void pendingEngineerTest() {
		aus.pendingEngineerUser(3);
	}
	
	@Test
	public void pendingCompanyTest() {
		aus.pendingCompanyUser(5);
	}
	
	@Test
	public void disapproveEngineerTest() {
		aus.disApprovedEngineerUser(7);
	}
	
	@Test
	public void disapproveCompanyTest() {
		aus.disApprovedCompanyUser(4);
	}
	
	@Test
	public void deletePublicTest() {
		aus.deletePublicAnnouncement(2);
	}
	
	@Test
	public void insertAdminUser() {
		aus.insertAdminUser(AdminUserDTO.builder().adminUserId("aaa").adminUserPw("bbb").adminUserName("홍길동")
				.adminUserTel("010-1234-5678").build());
		
	}
	
	@Test
	public void modifyAdminTest() {
		aus.modifyAdminUser(AdminUserDTO.builder().adminUserNo(6).adminUserId("aaa").adminUserPw("bbb123").adminUserName("홍길동")
				.adminUserTel("010-1234-5678").build());
	}
	
	@Test
	public void deleteAdminTest() {
		aus.deleteAdminUser(6);
	}
	
	@Test
	public void searchCompanyUserDisapprvedTest() {
		aus.searchDisApprovedCompanyUserByKeyword(PageRequestDTO.builder().keyword("").build()).getDtolist().forEach(x -> log.info("확인용 : "+x));
	}
	
	@Test
	public void searchCompanyUserPendingTest() {
		aus.searchPendingCompanyUserByKeyword(PageRequestDTO.builder().build()).getDtolist().forEach(x -> log.info("확인용 : "+x));
	}
	
	@Test
	public void searchEngineerUserDisapprovedTest() {
		aus.searchDisApprovedEngineerUserByKeyword(PageRequestDTO.builder().build()).getDtolist().forEach(x -> log.info("확인용 : "+x));
	}
	
	@Test
	public void searchEngineerUserPendingTest() {
		aus.searchPendingEngineerUserByKeyword(PageRequestDTO.builder().build()).getDtolist().forEach(x -> log.info("확인용 : "+x));
	}
}

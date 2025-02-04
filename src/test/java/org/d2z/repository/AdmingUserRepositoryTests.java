package org.d2z.repository;

import org.d2z.domain.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AdmingUserRepositoryTests {
	
	@Autowired
	private AdminUserRepository aur;
	
//	@Test
//	public void insertTest() {
//		aur.save(AdminUser.builder().adminUserId("aaa").adminUserPw("bbb").adminUserName("김").adminUserTel("010-1234-5678").build());
//	}
//	
//	@Test
//	public void manyInsertTest() {
//		for(int i=0;i<5;i++) {
//			aur.save(AdminUser.builder().adminUserId("a"+i).adminUserPw("b"+i).adminUserName("김"+i).adminUserTel("010-1234-56"+i).build());
//		}
//	}
//	
//	@Test
//	public void modifyTest() {
//		aur.save(AdminUser.builder().adminUserNo(2).adminUserId("").adminUserPw("").adminUserName("").adminUserTel("").build());
//	}
//	
//	@Test
//	public void deleteTest() {
//		aur.deleteById(2);
//	}
//	
//	@Test
//	public void readOneTest() {
//		log.info("확인용 : "+aur.findById(3).orElseThrow());
//	}
//	
}

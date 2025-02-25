package org.d2z.repository;

import org.d2z.domain.AdminUser;
import org.d2z.domain.Login;
import org.d2z.domain.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AdmingUserRepositoryTests {
	
	@Autowired
	private AdminUserRepository aur;
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private PasswordEncoder pe;
	
//	@Test
//	public void adminManySave() {
//		for(int i=0; i<5; i++) {
//			Login login = Login.builder().id("adminUser"+i).pw(pe.encode("1234")).build();
//			
//			login.addRole(MemberRole.AdminUser);
//			
//			lr.save(login);
//			
//			AdminUser adminUser = AdminUser.builder().adminUserName("관리자"+i).adminUserTel("010-1111-123"+i).login(login).build();
//			
//			aur.save(adminUser);
//		}
//	}
//	
	
	
	
	
	
//	@Test
//	public void adminSave() {
//		
//		Login login = Login.builder().id("admin1").pw(pe.encode("1234")).build();
//		
//		login.addRole(MemberRole.AdminUser);
//		
//		lr.save(login);
//		
//		AdminUser adminUser = AdminUser.builder().adminUserName("관리자1").adminUserTel("010-1111-1113").login(login).build();
//		
//		aur.save(adminUser);
//	}
	
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

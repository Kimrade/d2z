package org.d2z.repository;

import java.lang.reflect.Member;

import org.d2z.domain.EngineerUser;
import org.d2z.domain.Login;
import org.d2z.domain.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class EngineerUserRepositoryTests {
	
	@Autowired
	private EngineerUserRepository eur;
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private PasswordEncoder pe;
	
//	@Test
//	public void insertTestUnderSecurity() {
//		
//		Login login = Login.builder()
//						.id("member")
//						.pw(pe.encode("1111"))
//						.build();
//		
//		login.addRole(MemberRole.EngineerUser);
//		
//		log.info("확인용 : "+login);
//		
//		lr.save(login);
//		
//		EngineerUser engineerUser = EngineerUser.builder()
//						.engineerUserAdd("이거는 주소다")
//						.engineerUserCareer(4)
//						.engineerUserEmail("a@naver.com")
//						.engineerUserJob("주조")
//						.engineerUserMajorCompany("어느 주요 근무지")
//						.engineerUserPosition("팀장이였던것")
//						.engineerUserTel("010-2578-1565")
//						.login(login)
//						.build();
//		
//		log.info("확인용 : "+engineerUser);
//		
//		log.info("확인용 : "+eur.save(engineerUser));
//	}
//	
//	@Test
//	@Transactional
//	public void readTestUnderSercurity() {
//		log.info("확인용 : "+lr.findById("member").orElseThrow());
//		
//		log.info("확인용 : "+lr.findById("member").orElseThrow().getUserDiv());
//	}
//	
//	@Test
//	public void insertTest() {
//		eur.save(EngineerUser.builder().engineerUserId("aaa").engineerUserPw("bbb")
//	.engineerUserBirth("1975-01-01").engineerUserAdd("강원도 춘천시 춘천로 1234").engineerUserTel("010-1234-5678")
//	.engineerUserCareer(25).engineerUserMajorCompany("강원도팜교육원 풀스택 자바")
//	.engineerUserPosition("주조팀장").engineerUserJob("주조").build());
//	}
//	
//	@Test
//	public void manyInsertTest() {
//		for(int i=0; i<100;i++) {
//			eur.save(EngineerUser.builder().engineerUserId("aaa"+i).engineerUserPw("bbb"+i)
//										.engineerUserBirth("1975-01-01"+i).engineerUserAdd("강원도 춘천시 춘천로 1234"+i).engineerUserTel("010-1234-5678"+i)
//										.engineerUserCareer(25+i).engineerUserMajorCompany("강원도팜교육원 풀스택 자바"+i)
//										.engineerUserPosition("주조팀장"+i).engineerUserJob("주조"+i).build());
//		}
//	}
//	
//	@Test
//	public void modifyTest() {
//		eur.save(EngineerUser.builder().engineerUserNo(1).engineerUserId("aaa").engineerUserPw("bbde")
//				.engineerUserBirth("1980-01-01").engineerUserAdd("강원도 춘천시 춘천로 34").engineerUserTel("010-134-568")
//				.engineerUserCareer(20).engineerUserMajorCompany("강원도팜교육원 풀스택 자바")
//				.engineerUserPosition("안전팀장").engineerUserJob("안전").build());
//	}
//	
//	@Test
//	public void deleteTest1() {
//		eur.save(EngineerUser.builder().engineerUserNo(2).engineerUserId("aaa0").engineerUserPw("bbde")
//				.engineerUserBirth("1980-01-01").engineerUserAdd("강원도 춘천시 춘천로 34").engineerUserTel("010-134-568")
//				.engineerUserCareer(20).engineerUserMajorCompany("강원도팜교육원 풀스택 자바")
//				.engineerUserPosition("안전팀장").engineerUserJob("안전").isDeleted(1).build());
//	}
//	
//	@Test
//	public void deleteTest2() {
//		eur.deleteById(99);
//	}
//	
//	@Test
//	public void readOneTest() {
//		log.info("확인용 : "+eur.findById(1).orElseThrow());
//	}
//	
//	@Test
//	public void searchTest() {
//		
//		Pageable pageable = PageRequest.of(0, 10);
//		
//		eur.EngineerUserSearchByKeyword(null, null, pageable).stream().forEach(x -> log.info("확인용 : "+x));;
//	}
	
	
}

package org.d2z.repository;

import org.d2z.domain.CompanyUser;
import org.d2z.domain.Login;
import org.d2z.domain.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CompanyUserRepositoryTests {
	
	@Autowired
	private CompanyUserRepository cur;
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private PasswordEncoder pe;
	
//	@Test
//	public void insertManyCompanyUser() {
//		
//		for(int i=0 ; i< 25 ; i++) {
//			Login login = Login.builder().id("companyUser"+i).pw(pe.encode("1234")).build();
//			
//			login.addRole(MemberRole.CompanyUser);
//			
//			lr.save(login);
//			
//			CompanyUser companyUser = CompanyUser.builder().companyName("회사"+i)
//														.companyNo("111-22-112"+i)
//														.companyAdd("강원도 춘천시 어딘가"+i)
//														.companyInfo("이것은 "+i+"번째 회사의 소개 내용입니다.")
//														.companyNote("이것은 "+i+"번째 회사의 기타, 비고 내용입니다.")
//														.companySiteAdd("www.abc"+i+".com")
//														.companyTel("033-111-124"+i)
//														.companyUserEmail("abc"+i+"@naver.com")
//														.companyUserFax("033-123-220"+i)
//														.companyUserName("회사 담당자"+i)
//														.companyUserTel("033-123-224"+i)
//														.login(login).build();
//			
//			cur.save(companyUser);
//			
//		}
//		
//	}
	
//	@Test
//	public void insertTest() {
//		cur.save(CompanyUser.builder().companyUserId("aaa").companyUserPw("bbb").companyNo("111-22-33333")
//				.companyName("강원팜교육원 풀스택 자바").companyAdd("강원도 춘천시 효자동 123-39").companyTel("033-123-4567")
//				.companyUserFax("033-123-4560").companyUserEmail("a@naver.com").companySiteAdd("www.naver.com")
//				.companyUserName("d2z 팀").companyUserTel("010-1234-4567").build());
//	}
//	
//	@Test
//	public void manyInsertTest() {
//		for(int i =0; i< 100 ; i ++) {
//			cur.save(CompanyUser.builder().companyUserId("aaa"+i).companyUserPw("bbb"+i).companyNo("111-22-33333"+i)
//					.companyName("강원팜교육원 풀스택 자바"+i).companyAdd("강원도 춘천시 효자동 123-39"+i).companyTel("033-123-4567"+i)
//					.companyUserFax("033-123-4560"+i).companyUserEmail("a@naver.com"+i).companySiteAdd("www.naver.com"+i)
//					.companyUserName("d2z 팀"+i).companyUserTel("010-1234-4567"+i).build());
//		}
//	}
//	
//	@Test
//	public void modifyTesst() {
//		cur.save(CompanyUser.builder().companyUserNo(1).companyUserId("aaa").companyUserPw("bcd").companyNo("122-33-44444")
//				.companyName("강원팜교육원 풀스택 자바123").companyAdd("강원도 춘천시 효자동 395-48").companyTel("033-456-7890")
//				.companyUserFax("033-123-4234").companyUserEmail("123123a@naver.com").companySiteAdd("www.naver.com123123")
//				.companyUserName("d2z 팀").companyUserTel("010-1234-4567").build());
//	}
//	
//	@Test
//	public void deleteTest1() {
//		cur.save(CompanyUser.builder().companyUserNo(1).companyUserId("aaa").companyUserPw("bcd").companyNo("122-33-44444")
//				.companyName("강원팜교육원 풀스택 자바123").companyAdd("강원도 춘천시 효자동 395-48").companyTel("033-456-7890")
//				.companyUserFax("033-123-4234").companyUserEmail("123123a@naver.com").companySiteAdd("www.naver.com123123")
//				.companyUserName("d2z 팀").companyUserTel("010-1234-4567").isDeleted(1).build());
//	}
//	
//	@Test
//	public void deleteTest2() {
//		cur.deleteById(100);
//	}
//	
//	@Test
//	public void readOneTest() {
//		log.info("확인용 : "+cur.findById(2).orElseThrow());
//	}
//	
//	@Test
//	public void searchTest() {
//		Pageable pageable = PageRequest.of(0, 10);
//		
//		cur.companyUserSearchByKeyword(null, null, pageable).stream().forEach(x -> log.info("확인용 : "+x));
//	}
	
}

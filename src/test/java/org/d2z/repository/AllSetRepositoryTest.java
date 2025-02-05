package org.d2z.repository;

import org.d2z.domain.AdminUser;
import org.d2z.domain.CompanyUser;
import org.d2z.domain.EngineerUser;
import org.d2z.domain.PublicAnnouncement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AllSetRepositoryTest {
	
	@Autowired
	private AdminUserRepository aur;
	
	@Autowired
	private EngineerUserRepository eur;
	
	@Autowired
	private CompanyUserRepository cur;
	
	@Autowired
	private PublicAnnouncementRepository par;
	
	@Test
	public void allInsertTest() {
		
		for(int i=0;i<100;i++) {
			cur.save(CompanyUser.builder().companyUserId("aaa"+i).companyUserPw("bbb"+i).companyNo("111-22-33333"+i)
					.companyName("강원팜교육원 풀스택 자바"+i).companyAdd("강원도 춘천시 효자동 123-39"+i).companyTel("033-123-4567"+i)
					.companyUserFax("033-123-4560"+i).companyUserEmail("a@naver.com"+i).companySiteAdd("www.naver.com"+i)
					.companyUserName("d2z 팀"+i).companyUserTel("010-1234-4567"+i).build());
			
			eur.save(EngineerUser.builder().engineerUserId("aaa"+i).engineerUserPw("bbb"+i)
					.engineerUserBirth("1975-01-01"+i).engineerUserAdd("강원도 춘천시 춘천로 1234"+i).engineerUserTel("010-1234-5678"+i)
					.engineerUserCareer(25+i).engineerUserMajorCompany("강원도팜교육원 풀스택 자바"+i)
					.engineerUserPosition("주조팀장"+i).engineerUserJob("주조"+i).build());
				
		}
		
		for(int i=0;i<100;i++) {
			par.save(PublicAnnouncement.builder().announcementName("---- 공고 명"+i).announcementDescription("~~~~~~내용"+i)
					.serviceAdd("강원도 춘천시 "+i).serviceJob("주조"+i).serviceDiv("방문"+i).servicePeriod("6개월"+i).companyUser(cur.findById(2).orElseThrow()).build());
			
		}
		
		for(int i=0;i<5;i++) {
			aur.save(AdminUser.builder().adminUserId("a"+i).adminUserPw("b"+i).adminUserName("김"+i).adminUserTel("010-1234-56"+i).build());
		}
	}
}

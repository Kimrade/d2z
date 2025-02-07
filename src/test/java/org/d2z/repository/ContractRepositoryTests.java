package org.d2z.repository;

import org.d2z.domain.CompanyUser;
import org.d2z.domain.Contract;
import org.d2z.domain.EngineerUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ContractRepositoryTests {
	
	@Autowired
	private ContractRepository cr;
	
//	@Test
//	public void makeTest() {
//		cr.save(Contract.builder().contractName("사업 1")
//				.companyUser(CompanyUser.builder().companyUserNo(3).build())
//				.engineerUser(EngineerUser.builder().engineerUserNo(3).build()).build());
//	}
//	
//	@Test
//	public void searchBycompanyUserTest() {
//		cr.searchBycompanyUserNo(1).forEach(x -> log.info("확인용 : "+x));;
//	}
//	
//	@Test
//	public void searchByEngineerUserTest() {
//		cr.searchByEngineerUserNo(1).forEach(x -> log.info("확인용 : "+x));
//	}
//	
//	@Test
//	public void searchByNameTest() {
//		cr.searchByName("가").forEach(x -> log.info("확인용 : "+x));;
//	}
}
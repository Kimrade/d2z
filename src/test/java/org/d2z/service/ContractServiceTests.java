package org.d2z.service;

import org.d2z.dto.ContractDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ContractServiceTests {
	
	@Autowired
	private ContractService cs;
	
//	@Test
//	public void makeTest() {
//		cs.makeContract(ContractDTO.builder().companyUserNo(4).contractName("사업5")
//				.engineerUserNo(3).build());
//	}
//	
//	@Test
//	public void deleteContractTest() {
//		cs.deleteContract(4);
//	}
//	
//	@Test
//	public void readOneTest() {
//		log.info("확인용 : "+cs.readOneContract(1));
//	}
//	
//	@Test
//	public void searchOneByEngUserTest() {
//		cs.searchOneByEngineerUser(3).forEach(x -> log.info("확인용 : "+x));;
//	}
//	
//	@Test
//	public void searchOneByComUserTest() {
//		cs.searchOneByCompanyUser(4).forEach(x -> log.info("확인용 : "+x));
//	}
//	
	
}

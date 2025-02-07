package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.Contract;

public interface ContractSearch {	
	
	// 사업주 정보를 통해 조회가 가능
	public List<Contract> searchBycompanyUserNo(int companyUserNo);
	
	// 엔지니어 정보를 통해 조회가 가능
	public List<Contract> searchByEngineerUserNo(int engineerUserNo);
	
	
}

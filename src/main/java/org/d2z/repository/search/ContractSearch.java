package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.Contract;

public interface ContractSearch {	
	
	// 사업주 정보를 통해 조회가 가능
	public List<Contract> searchBycompanyUserNo(int companyUserNo);
	
	// 엔지니어 정보를 통해 조회가 가능
	public List<Contract> searchByEngineerUserNo(int engineerUserNo);
	
	// 사업명으로 사업정보를 조회가 가능
	public List<Contract> searchByName(String contractName);
	
	// 체결된 계약 갯수
	public int onGoingCount();
	
	// 완료된 계약 갯수
	public int completedCount();
}
package org.d2z.service;

import java.util.List;

import org.d2z.dto.ContractDTO;

public interface ContractService {
	
	// 계약 명세서 생성
	public void makeContract(ContractDTO contractDTO);
	
	// 계약 명세서 삭제
	public boolean deleteContract(int contractNo);
	
	// 계약 명세서 조회
	public ContractDTO readOneContract(int contractNo);	
	
	// 계약 명세서 수정
	public boolean modifyContract(ContractDTO contractDTO);
	
	// 계약자들의 정보를 통해 조회가 가능
	// 엔지니어 정보를 통해 조회가 가능
	public List<ContractDTO> searchOneByEngineerUser(int engineerUserNo);
	
	// 사업주 정보를 통해 조회가 가능
	public List<ContractDTO> searchOneByCompanyUser(int companyUserNo);
	
}
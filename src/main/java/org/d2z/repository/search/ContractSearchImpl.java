package org.d2z.repository.search;

import java.util.List;

import org.d2z.domain.Contract;
import org.d2z.domain.QContract;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;

public class ContractSearchImpl extends QuerydslRepositorySupport implements ContractSearch {

	public ContractSearchImpl() {
		super(Contract.class);
	}

	@Override
	public List<Contract> searchBycompanyUserNo(int companyUserNo) {
		QContract contract = QContract.contract;
		
		JPQLQuery<Contract> query = from(contract);
		
		if(companyUserNo != 0 && companyUserNo > 0) {
			query.where(contract.companyUser.companyUserNo.eq(companyUserNo));
		}
		
		List<Contract> list = query.fetch();
		
		return list;
	}

	@Override
	public List<Contract> searchByEngineerUserNo(int engineerUserNo) {
		
		QContract contract = QContract.contract;
		
		JPQLQuery<Contract> query = from(contract);
		
		if(engineerUserNo != 0 && engineerUserNo > 0) {
			query.where(contract.engineerUser.engineerUserNo.eq(engineerUserNo));
		}
		
		List<Contract> list = query.fetch();
		
		return list;
	}

	@Override
	public List<Contract> searchByName(String contractName) {
		
		QContract contract = QContract.contract;
		
		JPQLQuery<Contract> query = from(contract);
		
		if(contractName != null && contractName.length() > 0) {
			query.where(contract.contractName.contains(contractName));
		}
		
		List<Contract> list = query.fetch();
		
		return list;
	}
	
	

}

package org.d2z.repository;

import org.d2z.domain.Contract;
import org.d2z.repository.search.ContractSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer>, ContractSearch {
	
	
	
}

package org.d2z.repository;

import java.util.Optional;

import org.d2z.domain.CompanyUser;
import org.d2z.repository.search.CompanyUserSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Integer>, CompanyUserSearch {
	
	// id로 정보 찾기
	Optional<CompanyUser> findByLoginId(String id);
	
	// 이메일로 정보 찾기
	Optional<CompanyUser> findByCompanyUserEmail(String companyUserEmail);
	
	// 전화번호로 정보 찾기
	Optional<CompanyUser> findByCompanyUserTel(String companyUserTel);
	
}

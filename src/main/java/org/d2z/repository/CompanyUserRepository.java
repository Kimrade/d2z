package org.d2z.repository;

import org.d2z.domain.CompanyUser;
import org.d2z.repository.search.CompanyUserSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Integer>, CompanyUserSearch {

}

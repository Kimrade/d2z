package org.d2z.repository;

import org.d2z.domain.EngineerUser;
import org.d2z.repository.search.EngineerUserSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineerUserRepository extends JpaRepository<EngineerUser, Integer>,EngineerUserSearch {

}

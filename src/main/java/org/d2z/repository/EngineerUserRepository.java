package org.d2z.repository;

import java.util.Optional;

import org.d2z.domain.EngineerUser;
import org.d2z.repository.search.EngineerUserSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineerUserRepository extends JpaRepository<EngineerUser, Integer>,EngineerUserSearch {
	
	// id로 엔지니어 사용자 정보 찾기
	Optional<EngineerUser> findByLoginId(String id);
	
	// 이메일로 엔지니어 사용자 정보 찾기
	Optional<EngineerUser> findByEngineerUserEmail(String engineerUserEmail);
	
	// 전화번호로 엔지니어 사용자 정보 찾기
	Optional<EngineerUser> findByEngineerUserTel(String engineerUserTel);
}

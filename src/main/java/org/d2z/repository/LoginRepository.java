package org.d2z.repository;

import java.util.Optional;

import org.d2z.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {
	
	// 아이디를 사용해서 정보를 가져옴
	Optional<Login> findById(String id);
	
}
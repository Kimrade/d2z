package org.d2z.repository;

import java.util.Optional;

import org.d2z.domain.Login;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Integer> {
	
	// 아이디를 사용해서 정보를 가져옴
	Optional<Login> findById(String id);
	
	@EntityGraph(attributePaths = "userDiv")
	@Query("SELECT l FROM Login l JOIN FETCH l.userDiv WHERE l.id = :id")
	Optional<Login> findByIdWithRoles(@Param("id") String id);
	
}
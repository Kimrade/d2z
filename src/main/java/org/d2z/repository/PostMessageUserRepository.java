package org.d2z.repository;

import java.util.Optional;

import org.d2z.domain.PostMessageUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMessageUserRepository extends JpaRepository<PostMessageUser, Integer> {
	
	// 아이디로 찾기
	Optional<PostMessageUser> findById(String id);
	
	
}

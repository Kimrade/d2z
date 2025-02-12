package org.d2z.repository;

import org.d2z.domain.PostMessageUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMessageUserRepository extends JpaRepository<PostMessageUser, Integer> {
	
	// 아이디로 찾기
	PostMessageUser findById(String id);
	
	
}

package org.d2z.repository;

import org.d2z.domain.PostMessage;
import org.d2z.repository.search.PostMessageSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMessageRepository extends JpaRepository<PostMessage, Integer>, PostMessageSearch {
	
	
	
	
	
}

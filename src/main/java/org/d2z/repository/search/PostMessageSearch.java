package org.d2z.repository.search;

import org.d2z.domain.PostMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostMessageSearch {
	
	// 페이지 처리 - 수신쪽 확인
	Page<PostMessage> findbySenderId(String id, Pageable pageable);
	
	// 페이지 처리 - 송신쪽 확인
	Page<PostMessage> findbyReceiverId(String id, Pageable pageable);
	
}

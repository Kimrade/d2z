package org.d2z.service;

import java.util.List;

import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PostMessageDTO;
import org.d2z.dto.PostMessageUserDTO;

public interface PostMessageService {
	
	// 메세지 쓰기
	public PostMessageDTO writeMessage(PostMessageDTO postMessageDTO);
	
	// 보낸 메세지 리스트 출력(페이지 처리)
	public List<PostMessageDTO> sentMessage(String id, PageRequestDTO pageRequestDTO);
	
	// 받은 메세지 리스트 출력(페이지 처리)
	public List<PostMessageDTO> receivedMessage(String id, PageRequestDTO pageRequestDTO);
	
	// 수신쪽 메세지 삭제
	public boolean deleteByReceivedPart(int no, PostMessageUserDTO userDTO);
	
	// 송신쪽 메세지 삭제
	public boolean deleteBySentPart(int no, PostMessageUserDTO userDTO);
}
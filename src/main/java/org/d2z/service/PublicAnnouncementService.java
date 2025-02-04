package org.d2z.service;

import org.d2z.dto.PageRequestDTO;
import org.d2z.dto.PageResponseDTO;
import org.d2z.dto.PublicAnnouncementDTO;

public interface PublicAnnouncementService {
	
	// 공고 게시글 등록
	public boolean publicAnnouncementInsert(PublicAnnouncementDTO publicAnnouncementDTO);
	
	// 공고 게시글 수정
	public boolean publicAnnouncementModify(PublicAnnouncementDTO publicAnnouncementDTO);
	
	// 공고 게시글 삭제
	public boolean publicAnnouncementDelete(int announcementNo);
	
	// 공고 게시글 상세 조회
	public PublicAnnouncementDTO publicAnnouncementReadOne(int announcementNo);
	
	// 공고 게시글 검색, 조회, 페이지 처리
	public PageResponseDTO<PublicAnnouncementDTO> publicAnnouncementSearchByKeyword(PageRequestDTO pageRequestDTO);
}

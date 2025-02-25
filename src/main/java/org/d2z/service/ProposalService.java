package org.d2z.service;

import java.util.List;

import org.d2z.dto.ProposalDTO;

public interface ProposalService {
	
	// 제안서 작성
	public boolean sendProposal(ProposalDTO proposalDTO);	
	
	// 제안서 삭제
	public boolean deleteProposal(int proposalNo);
	
	// 제안서 정보 불러오기 (공고문 번호에 대해)
	public List<ProposalDTO> listProposalByAnnouncementNo(int announcementNo);
}
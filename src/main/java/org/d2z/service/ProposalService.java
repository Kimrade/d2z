package org.d2z.service;

import org.d2z.dto.ProposalDTO;

public interface ProposalService {
	
	// 제안서 작성
	public void sendProposal(ProposalDTO proposalDTO);	
	
	// 제안서 삭제
	public boolean sendProposal(int proposalNo);
}
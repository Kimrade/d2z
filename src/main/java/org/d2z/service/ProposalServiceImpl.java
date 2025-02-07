package org.d2z.service;

import org.d2z.domain.Proposal;
import org.d2z.dto.ProposalDTO;
import org.d2z.repository.ProposalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProposalServiceImpl implements ProposalService {
	
	private final ProposalRepository pr;
	private final ModelMapper modelMapper;
	
	
	@Override
	public void sendProposal(ProposalDTO proposalDTO) {
		pr.save(modelMapper.map(proposalDTO , Proposal.class));
	}


	@Override
	public boolean sendProposal(int proposalNo) {
		
		boolean result = false;
		
		if(pr.findById(proposalNo).isPresent()) {
			pr.deleteById(proposalNo);
			result = true;
		}
		
		return result;
	}

}

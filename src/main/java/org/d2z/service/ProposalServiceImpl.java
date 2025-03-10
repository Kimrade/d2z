package org.d2z.service;

import java.util.List;
import java.util.stream.Collectors;

import org.d2z.domain.Proposal;
import org.d2z.dto.ProposalDTO;
import org.d2z.repository.ProposalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProposalServiceImpl implements ProposalService {
	
	private final ProposalRepository pr;
	private final ModelMapper modelMapper;
	
	
	@Override
	@Transactional
	public boolean sendProposal(ProposalDTO proposalDTO) {
		
		boolean result = false;
		
		if(pr.findByEngineerUser_EngineerUserNoAndPublicAnnouncement_AnnouncementNo(proposalDTO.getEngineerUserNo(), proposalDTO.getAnnouncementNo()).isPresent()) {
			
		}else {
			pr.save(modelMapper.map(proposalDTO , Proposal.class));
			result = true;
		}
		
		return result;
	}


	@Override
	@Transactional
	public boolean deleteProposal(int proposalNo) {
		
		boolean result = false;
		
		if(pr.findById(proposalNo).isPresent()) {
			pr.deleteById(proposalNo);
			result = true;
		}
		
		return result;
	}


	@Override
	public List<ProposalDTO> listProposalByAnnouncementNo(int announcementNo) {
		
		return pr.findByAnnouncementNo(announcementNo).stream().map(x -> modelMapper.map(x, ProposalDTO.class)).collect(Collectors.toList());
	}

}

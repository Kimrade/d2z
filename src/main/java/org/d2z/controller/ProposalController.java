package org.d2z.controller;

import java.util.List;

import org.d2z.dto.ProposalDTO;
import org.d2z.service.ProposalService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/proposal")
public class ProposalController {
	
	private final ProposalService ps;
	
	// 제안서 보내기 - 생성
	@PostMapping("/send")
	public void sendProposal(@RequestBody ProposalDTO proposalDTO) {
		ps.sendProposal(proposalDTO);
	}
	
	// 제안서 제거
	@DeleteMapping("/delete/{proposalNo}")
	public boolean deleteProposal(@PathVariable("proposalNo")int proposalNo) {
		return ps.deleteProposal(proposalNo);
	}
	
	// 제안서 list 기록 가져오기
	@GetMapping("/list/{announcementNo}")
	public List<ProposalDTO> listByAnnouncementNo(@PathVariable("announcementNo") int announcementNo){
		return ps.listProposalByAnnouncementNo(announcementNo);
	}
}

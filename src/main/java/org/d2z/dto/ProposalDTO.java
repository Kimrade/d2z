package org.d2z.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDTO {
	
	private int proposalNo;
	
	private LocalDateTime sendProposalDate;
	
	private int engineerUserNo;
	
	private int announcementNo;
	
	
}

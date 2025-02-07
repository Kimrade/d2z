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
public class ContractDTO {
	
	private int contractNo;
	
	private String contractName;
	
	private int processingTask;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifyDate;
	
	private int engineerUserNo;
	
	private int companyUserNo;
		
}
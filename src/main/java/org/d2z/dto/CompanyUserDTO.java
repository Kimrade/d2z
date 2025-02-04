package org.d2z.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUserDTO {
	
	private int companyUserNo;
	
	private String companyUserId;
	
	private String companyUserPw;
	
	private String companyNo;
	
	private String companyName;
	
	private String companyAdd;
	
	private String companyTel;
	
	private String companyUserFax;
	
	private String companyUserEmail;
	
	private String companyUserSiteAdd;
	
	private String companyUserName;
	
	private String companyUserTel;
	
}

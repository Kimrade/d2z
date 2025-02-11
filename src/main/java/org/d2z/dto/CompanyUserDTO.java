package org.d2z.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUserDTO {
	
	private int companyUserNo;
	
	private String companyUserEmail;
	
	private String companyNo;
	
	private String companyName;
	
	private String companyAdd;
	
	private String companyTel;
	
	private String companyUserFax;
	
	private String companySiteAdd;
	
	private String companyUserName;
	
	private String companyUserTel;
	
	private String companyInfo;
	
	private String companyNote;
	
	private int isDeleted;
	
	private int isApproved;
	
	private int userNo;
	
	private String id;
	
	private int userDiv;
	
	private String pw;
}

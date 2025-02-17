package org.d2z.dto;

import org.d2z.domain.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EngineerUserDTO {
	
	private int engineerUserNo;
	
	private String engineerUserName;
	
	private String engineerUserBirth;
	
	private String engineerUserAdd;
	
	private String engineerUserTel;
	
	private double engineerUserCareer;
	
	private String engineerUserMajorCompany;
	
	private String engineerUserPosition;
	
	private String engineerUserJob;
	
	private String engineerUserEmail;
	
	private String engineerUserNote;
	
	private String engineerUserInfo;
	
	private int isDeleted;
	
	private int isApproved;
	
	private int userNo;
	
	private String id;
	
	private MemberRole userDiv;
	
	private String pw;
}

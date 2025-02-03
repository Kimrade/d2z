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
public class EngineerUserDTO {
	
	private int engineerUserNo;
	
	private String engineerUserId;
	
	private String engineerUserPw;
	
	private String engineerUserBirth;
	
	private String engineerUserAdd;
	
	private String engineerUserTel;
	
	private LocalDateTime engineerUserCareer;
	
	private String engineerUserMajorCompany;
	
	private String engineerUserPosition;
	
	private String engineerUserJob;
}

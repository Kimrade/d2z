package org.d2z.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO extends User{
	
	public LoginDTO(String username, String password, int userNo,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
		this.userNo = userNo;
		this.id = username;
		this.pw = password;
	}

	private int userNo;
	
	private String id;
	
	private String pw;
		
}
package org.d2z.security;

import java.util.Optional;
import java.util.stream.Collectors;

import org.d2z.domain.Login;
import org.d2z.dto.LoginDTO;
import org.d2z.repository.LoginRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final LoginRepository lr;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("LoadUserByUserName : "+username);
		
		Optional<Login> result = lr.findById(username);
		
		if(result.isEmpty()) {
			// 해당 id의 사용자가 없으면
			throw new UsernameNotFoundException("username Not Found......");
		}
		
		Login login = result.get();
		
		LoginDTO loginDTO = new LoginDTO(
				login.getId(),
				login.getPw(),
				login.getUserNo(),
				login.getUserDiv().stream().map(x -> new SimpleGrantedAuthority("ROLE_"+x.name())).collect(Collectors.toList())
				);
		
		log.info("확인용 : "+loginDTO.getId());
		log.info("확인용 : "+loginDTO.getPassword());
		
		return loginDTO;
	}
	
	
	
	
	
	
}

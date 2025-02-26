package org.d2z.config;

import javax.sql.DataSource;

import org.d2z.security.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {
	
	private final DataSource dataSource;
	private final CustomUserDetailsService userDetailsService;
	private final CustomAuthenticationSuccessHandler successHander;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
	    log.info("-------------------------------configure--------------------------------");

	    http.authorizeHttpRequests(x -> x.requestMatchers("/company/**").hasRole("CompanyUser")
	                                    .requestMatchers("/admin/**").hasRole("AdminUser")
	                                    .requestMatchers("/engineer/**").hasRole("EngineerUser")
	                                    .requestMatchers("/ws/**").permitAll()
	                                    .requestMatchers("/chat/api/**").permitAll()
	                                    .anyRequest().permitAll());

	    http.cors().disable();

	    http.formLogin(x -> x.loginPage("/d2z/login")
	                         .failureForwardUrl("/d2z/loginError")
	                         .successHandler(successHander));

	    http.logout(x -> x.logoutUrl("/d2z/logout")
	                      .logoutSuccessUrl("/d2z/login?logout")
	                      .invalidateHttpSession(true)
	                      .deleteCookies("JSESSIONID")
	                      .permitAll());

	    http.rememberMe(x -> x
	            .key("d2zSecretAndUnique")
	            .tokenRepository(persistentTokenRepository())
	            .userDetailsService(userDetailsService)
	            .tokenValiditySeconds(60 * 60 * 24 * 30));

	    http.addFilterBefore((request, response, chain) -> {
	        log.info("🔍 [Security] 요청 URL: " + request.getRequestId());
	        chain.doFilter(request, response);
	    }, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
	private PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		
		log.info("------------------------------web configure-----------------------------------");
		
		// 정적자원 filter 적용 x => css와 같은 것들
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	// password encoder -> 사용안하면 패스워드 인코더 없음 오류 발생
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}

package org.d2z.config;

import java.io.IOException;
import java.util.Collection;

import org.d2z.domain.MemberRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals(MemberRole.AdminUser.getAuthority()))) {
            return "/admin/main";
        } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals(MemberRole.EngineerUser.getAuthority()))) {
            return "/engineer/main";
        } else if(authorities.stream().anyMatch(auth -> auth.getAuthority().equals(MemberRole.CompanyUser.getAuthority()))) {
        	return "/company/main";
        } else {
            return "/d2z/"; // 기본 페이지
        }
    }
	
	
	
	
}

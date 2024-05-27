package com.univaq.TestAgile.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String redirectURL = request.getContextPath();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                redirectURL = "/admin/dashboard";
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                redirectURL = "/user/dashboard";
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_REFERENTE")) {
                redirectURL = "/referente/dashboard";
                break;
            }
        }

        response.sendRedirect(redirectURL);
    }
}

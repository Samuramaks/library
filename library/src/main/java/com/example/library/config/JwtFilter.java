package com.example.library.config;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.util.StringUtils;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import com.example.library.config.MyuserDetailsService.CustomUserDetails;
import com.example.library.config.MyuserDetailsService.CustomUserDetailsService;


@Log4j2
@Component
public class JwtFilter extends GenericFilterBean{
    public static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse serveletResponse, FilterChain filterChain)
            throws IOException, ServletException {
                log.info("do filter...");
                String token = getTokenFromRequest((HttpServletRequest) servletRequest);
                if(token != null && jwtProvider.validateToken(token)){
                    String userLogin = jwtProvider.getLoginFromToken(token);
                    CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userLogin);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null,  customUserDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            filterChain.doFilter(servletRequest, serveletResponse);
        }



    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }
    
}

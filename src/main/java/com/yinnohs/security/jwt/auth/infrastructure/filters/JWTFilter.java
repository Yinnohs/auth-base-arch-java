package com.yinnohs.security.jwt.auth.infrastructure.filters;

import com.yinnohs.security.jwt.auth.domain.exceptions.TokenNotFoundException;
import com.yinnohs.security.jwt.auth.infrastructure.services.JWTServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JWTFilter extends OncePerRequestFilter {

    private final String AUTHENTICATION_HEADER = "Authorization";
    private final String BEARER = "Bearer ";
    private final JWTServiceImpl jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(AUTHENTICATION_HEADER);
        String token = null;
        String accountEmail = null;

        if (authHeader == null || !authHeader.startsWith(BEARER)){
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(BEARER.length());
        accountEmail = jwtService.extractAccountEmail(token);
        boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication() != null;

        if (accountEmail != null || !isAuthenticated){
            UserDetails userDetails = userDetailsService.loadUserByUsername(accountEmail);

            boolean isValidToken = jwtService.validateToken(token, userDetails);
            var authorities = userDetails.getAuthorities();

            if (isValidToken){
                var userPasswordAuthToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                var requestParams = new WebAuthenticationDetailsSource().buildDetails(request);
                userPasswordAuthToken.setDetails(requestParams);
                SecurityContextHolder.getContext().setAuthentication(userPasswordAuthToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}

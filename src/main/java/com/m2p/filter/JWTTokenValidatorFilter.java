package com.m2p.filter;

import com.m2p.constant.JWTConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt =request.getHeader(JWTConstant.JWT_HEADER);
        try{
        if(null!=jwt) {
            SecretKey key = Keys.hmacShaKeyFor(JWTConstant.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)//checking if jwt token is valid and all senario, otherwise it will give different exceception if its invalid
                    .getBody();
            String userName = String.valueOf(claims.get("User-name"));
            String authorities = String.valueOf(claims.get("authorities"));
            Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        }
        catch(Exception e){
throw new BadCredentialsException("token invalid ");
        }
        filterChain.doFilter(request,response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/notices");

        // if we want to generate jwt token only on login time so other time jwt token will not create
    }
}

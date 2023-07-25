package com.m2p.filter;

import com.m2p.constant.JWTConstant;
import com.sun.jdi.event.StepEvent;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    @Value("${jwt.expiry.time}")
    String expiredJWTTokenTime ;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            SecretKey key= Keys.hmacShaKeyFor(JWTConstant.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt= Jwts.builder().setIssuer("ASHEESH").setSubject("jwt token")
                    .claim("User-name",authentication.getName())
                    .claim("authorities",String.join("ROLE_USER","ROLE_ADMIN"))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime()+3000))
                            .signWith(key).compact();

            response.setHeader(JWTConstant.JWT_HEADER,jwt);

        }
        filterChain.doFilter(request,response);
    }


@Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/notices");

    // if we want to generate jwt token only on login time so other time jwt token will not create
    }


}

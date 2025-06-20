package com.example.demo.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTRequestFilter implements jakarta.servlet.Filter {

 @Autowired
 private JWTUtil jwtUtil;

 @Autowired
 private MyUserDetailsService userDetailsService;

 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
         throws IOException, jakarta.servlet.ServletException {

     HttpServletRequest httpRequest = (HttpServletRequest) request;
     String authHeader = httpRequest.getHeader("Authorization");
     if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
         String token = authHeader.substring(7);
         if (jwtUtil.validateToken(token)) {
             String username = jwtUtil.extractUsername(token);
             UserDetails userDetails = userDetailsService.loadUserByUsername(username);
             Authentication auth = new UsernamePasswordAuthenticationToken(
                     userDetails, null, userDetails.getAuthorities());
             SecurityContextHolder.getContext().setAuthentication(auth);
         }
     }
     chain.doFilter(request, response);
 }
}
 
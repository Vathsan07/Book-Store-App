package com.example.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 if ("user".equals(username)) {
         return User.withUsername(username)
                 .password("{noop}password") // {noop} plain text for demo
                 .roles("USER")
                 .build();
     } else {
         throw new UsernameNotFoundException(username + " not found");
     }
 }
}
 
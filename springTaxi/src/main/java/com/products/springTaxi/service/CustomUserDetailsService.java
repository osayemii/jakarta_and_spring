package com.products.springTaxi.service;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.products.springTaxi.entity.User;
import com.products.springTaxi.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   private final UserRepository repository;

   public CustomUserDetailsService(UserRepository repository) {
       this.repository = repository;
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

       User user = repository.findByEmail(email)
                   .orElseThrow(() ->
                   new UsernameNotFoundException("User not found"));

       return org.springframework.security.core.userdetails.User
               .withUsername(user.getEmail())
               .password(user.getPassword())
               .authorities("USER")
               .build();
   }


}




package com.example.springsecuritydemo.services;

import com.example.springsecuritydemo.Repository.UserRepo;
import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.model.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            System.out.println("User 404");
            throw new UsernameNotFoundException("User 404 not found");
        }
        return new UserPrincipal(user);
    }
}

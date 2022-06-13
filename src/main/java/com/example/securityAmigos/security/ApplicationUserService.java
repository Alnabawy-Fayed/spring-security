package com.example.securityAmigos.security;

import com.example.securityAmigos.dao.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class ApplicationUserService implements UserDetailsService {
    ApplicationUser applicationUser;
    @Autowired
    public ApplicationUserService(ApplicationUser applicationUser){
        this.applicationUser = applicationUser;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUser.selectApplicationUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}

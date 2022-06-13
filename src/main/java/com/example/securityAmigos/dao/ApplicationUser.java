package com.example.securityAmigos.dao;

import com.example.securityAmigos.security.MyAppUser;

import java.util.Optional;

public interface ApplicationUser {
    Optional<MyAppUser> selectApplicationUserByUsername(String userName);
}

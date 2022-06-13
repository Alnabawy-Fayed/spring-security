package com.example.securityAmigos.dao;

import com.example.securityAmigos.security.MyAppUser;
import com.example.securityAmigos.security.UserRoles;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.securityAmigos.security.UserRoles.*;

@Service
public class ApplicationUserRepo implements ApplicationUser{
    PasswordEncoder passwordEncoder;
    @Autowired
    public ApplicationUserRepo(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Optional<MyAppUser> selectApplicationUserByUsername(String userName) {
        return getUsers().
                stream().filter(e -> userName.equals(e.getUsername())).findFirst();
    }
    public List<MyAppUser> getUsers(){
        List<MyAppUser> myAppUsers = Lists.newArrayList(
                new MyAppUser("nabawy",passwordEncoder.encode("123"), STUDENT.getGrantedAuthority(),true,
                        true,
                        true,
                        true),
                new MyAppUser("younis",passwordEncoder.encode("123"), ADMIN.getGrantedAuthority(),true,
                        true,
                        true,
                        true),
                new MyAppUser("mohamed",passwordEncoder.encode("123"), ADMINTRAINEE.getGrantedAuthority(),true,
                        true,
                        true,
                        true)
        );
        return myAppUsers;
    }
}

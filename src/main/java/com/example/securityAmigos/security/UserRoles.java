package com.example.securityAmigos.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.securityAmigos.security.UserPermissions.*;

public enum UserRoles {
    STUDENT(Sets.newHashSet())
    ,ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE))
    ,ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));
    private final Set<UserPermissions> permissionsSet;

    UserRoles(Set<UserPermissions> userPermissions) {
        this.permissionsSet = userPermissions;
    }

    public Set<UserPermissions> getPermissionsSet() {
        return permissionsSet;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions = getPermissionsSet().stream().
                map(e -> new SimpleGrantedAuthority(e.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;

    }
}

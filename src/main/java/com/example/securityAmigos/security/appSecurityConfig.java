package com.example.securityAmigos.security;

import com.example.securityAmigos.controllers.Student;
import com.example.securityAmigos.dao.ApplicationUserRepo;
import com.example.securityAmigos.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.securityAmigos.security.UserPermissions.COURSE_WRITE;
import static com.example.securityAmigos.security.UserRoles.*;

@Configuration
@EnableWebSecurity
public class appSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationUserService applicationUserService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .authorizeRequests()
                .antMatchers("/","index")
                .permitAll()
                .antMatchers("/api/**")
                .hasRole(STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"/api/studentmanager").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/api/studentmanager").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST,"/api/studentmanager").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/api/studentmanager").hasAnyRole(STUDENT.name(),ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest()
                .authenticated();
//                .and()
//                .formLogin()
//                .permitAll().and().rememberMe()
//                .and().logout();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserService);
        return daoAuthenticationProvider;

    }

//    @Override
//    @Bean
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        UserDetails nabawy = User.builder()
//                .username("nabawy")
//                .password(passwordEncoder.encode("123"))
//                .roles(STUDENT.name())
//                .authorities(STUDENT.getGrantedAuthority())
//                .build();
//        UserDetails younis = User.builder()
//                .username("younis")
//                .password(passwordEncoder.encode("123"))
//                .roles(ADMIN.name())
//                .authorities(ADMIN.getGrantedAuthority())
//                .build();
//        UserDetails mohamed = User.builder()
//                .username("mohamed")
//                .password(passwordEncoder.encode("123"))
//                .roles(ADMINTRAINEE.name())
//                .authorities(ADMINTRAINEE.getGrantedAuthority())
//                .build();
//        return new InMemoryUserDetailsManager(nabawy,younis,mohamed);
//    }
}

package com.example.securityAmigos.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    AuthenticationManager authenticationManager;
    @Autowired
            public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
            }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper().
                    readValue(request.getInputStream()
                            ,UsernameAndPasswordAuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUserName(),
                    authenticationRequest.getPassword()
            );
            Authentication athenticate = authenticationManager.authenticate(authentication);
            return athenticate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
       String key = "abcdefghijklmnopojkksdlfklkdsslkdoiasdfsldlabcdefghijklmnopojkksdlfklkdsslkdoiasdfsldlajsdabcdefghijklmnopojkksdlfklkdsslkdoiasdfsldlajsdajsd";
       String token = Jwts.builder()
               .setSubject(authResult.getName())
               .claim("authorities",authResult.getAuthorities())
               .setIssuedAt(new Date())
               .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
               .signWith(Keys.hmacShaKeyFor(key.getBytes()))
               .compact();
       response.setHeader("Authorization","Bear"+token);
    }
}

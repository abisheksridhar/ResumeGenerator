package com.example.demo.jwt.utility;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.capgemini.userService.Entities.AppUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class JwtUtility {
    @Value("spring.securityKey")
    private String securityKey;
    public String generateToken(AppUser user){
        return JWT.create().withArrayClaim("Authorities",user.getRole().getAuthorities())
                .withExpiresAt(new Date(System.currentTimeMillis()*24*60*60*1000))
                .withSubject(user.getUsername())
                .sign(Algorithm.HMAC512(securityKey));
    }

    private JWTVerifier verifier()
    {
        return JWT.require(Algorithm.HMAC512(securityKey)).build();
    }

    public Boolean isValidToken(String token)
    {
        String Username = getSubjectFromToken(token);
        return StringUtils.isNotBlank(Username) && !isExpiredToken(token);
    }

    private boolean isExpiredToken(String token) {
        JWTVerifier verifier = verifier();
        return verifier.verify(token).getExpiresAt().before(new Date());
    }

    public String getSubjectFromToken(String token) {
        JWTVerifier verifier = verifier();
        return verifier.verify(token).getSubject();
    }

    public List<GrantedAuthority> getAuthorityFromToken(String token)
    {
        String claims[] = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public  String[] getClaimsFromToken(String token)
    {
        JWTVerifier verifier = verifier();
        return verifier.verify(token).getClaim("Authorities").asArray(String.class);
    }


}

package com.example.demo.jwt.filter;

import com.capgemini.userService.jwt.utility.JwtUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.stream;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private JwtUtility utility;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] allowedUrls = {"/user/login","/user/register"};
        return stream(allowedUrls).anyMatch((url)->new AntPathRequestMatcher(url).matches(request));
    }

    public JWTFilter(JwtUtility utility) {
        this.utility = utility;
    }

    Authentication authentication(String username, List<GrantedAuthority> authorityList,HttpServletRequest request){
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username,null,authorityList);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getMethod().equalsIgnoreCase("OPTIONS"))
        {
            response.setStatus(HttpStatus.OK.value());
        }
        else
        {
            String authority = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(authority != null && authority.startsWith("Bearer"))
            {
                String token = authority.substring(7);
                String Username = utility.getSubjectFromToken(token);
                if(utility.isValidToken(token))
                {
                    List<GrantedAuthority> authorityList = utility.getAuthorityFromToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication(Username,authorityList,request));
                    filterChain.doFilter(request,response);
                    return;
                }
                else {
                    response.setStatus(HttpStatus.LOCKED.value());
                }
            }
            else {
                response.setStatus(HttpStatus.FORBIDDEN.value());
            }
        }
    }
}

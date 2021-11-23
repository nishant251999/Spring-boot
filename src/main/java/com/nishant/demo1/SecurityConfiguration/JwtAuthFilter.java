package com.nishant.demo1.SecurityConfiguration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nishant.demo1.services.MyUserDetailsService;
import com.nishant.demo1.utils.JwtUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if( requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {

            token = requestTokenHeader.substring(7);

            try {
                username = jwtUtility.getUsernameFromToken(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails
                    = myUserDetailsService.loadUserByUsername(username);

            if(jwtUtility.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
                
    }


    
}

package com.example.fileservice.config;

import com.example.fileservice.core.UserContext;
import com.example.fileservice.dto.UserDto;
import com.example.fileservice.service.AuthRestService;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

//@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserContextDetectFilter extends OncePerRequestFilter {
    @Resource
    UserContext userContext;

    @Autowired
    AuthRestService authRestService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null) {
            UserDto user = authRestService.getCurrentUser(bearerToken).getBody();
            userContext.setId(user.getId());
            userContext.setUsername(user.getUsername());
            userContext.setEmail(user.getEmail());
            userContext.setRoles(user.getRoles());
            userContext.setFirstName(user.getFirstName());
            userContext.setLastName(user.getLastName());
            userContext.setPhone(user.getPhone());
            userContext.setReceiveAddress(user.getReceiveAddress());
            userContext.setDeliveryAddress(user.getDeliveryAddress());
            userContext.setGender(user.getGender());
            userContext.setAvatarUrl(user.getAvatarUrl());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                    List.of(new SimpleGrantedAuthority(user.getRoles().name())));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}

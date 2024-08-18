package com.example.fileservice.service;

import com.example.fileservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "spring-cloud-auth")
public interface AuthRestService {

    @GetMapping("/auth/usercontext")
    ResponseEntity<UserDto> getCurrentUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token);

//    @GetMapping("/auth/me")
//    UserEntity findByUsername(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token);
}

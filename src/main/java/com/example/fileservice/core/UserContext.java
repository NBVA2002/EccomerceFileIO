package com.example.fileservice.core;

import com.example.fileservice.model.Address;
import com.example.fileservice.model.enumerated.Gender;
import com.example.fileservice.model.enumerated.Role;
import com.example.fileservice.model.enumerated.UserStatus;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class UserContext {
    private Long id;

    private String username;

    private String email;

    private Role roles;

    private String firstName;

    private String lastName;

    private String phone;

    private List<Address> receiveAddress;
    private Address deliveryAddress;

    private Gender gender;

    private String avatarUrl;

    private UserStatus status;

    private String jwt;
}

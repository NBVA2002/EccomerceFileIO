package com.example.fileservice.dto;

import com.example.fileservice.core.BaseDto;
import com.example.fileservice.model.Address;
import com.example.fileservice.model.enumerated.Gender;
import com.example.fileservice.model.enumerated.Role;
import com.example.fileservice.model.enumerated.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {
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

}

package com.hotel.api.response;

import com.hotel.api.model.User_Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// acts as a message carrier to the user
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String jwt;
    private String message;
    private User_Role role;
}

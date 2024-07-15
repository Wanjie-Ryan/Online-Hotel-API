package com.hotel.api.controller;


import com.hotel.api.config.JwtProvider;
import com.hotel.api.model.User;
import com.hotel.api.repository.CartRepository;
import com.hotel.api.repository.UserRepository;
import com.hotel.api.service.CustomUserDetailsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CartRepository cartRepo;

    public ResponseEntity<AuthResponse> createrUser(@RequestBody User user){

    }



}

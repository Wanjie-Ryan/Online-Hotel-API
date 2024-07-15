package com.hotel.api.controller;


import com.hotel.api.config.JwtProvider;
import com.hotel.api.model.Cart;
import com.hotel.api.model.User;
import com.hotel.api.model.User_Role;
import com.hotel.api.repository.CartRepository;
import com.hotel.api.repository.UserRepository;
import com.hotel.api.request.LoginRequest;
import com.hotel.api.response.AuthResponse;
import com.hotel.api.service.CustomUserDetailsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

    // creating the registration functionality
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> createdUser(@RequestBody User user) throws Exception {

        User emailExist = userRepo.findByEmail(user.getEmail());

        if(emailExist !=null){
            throw new Exception("Email is already used with another account");
        }

        User createdUser = new User();
        createdUser.setName(user.getName());
        createdUser.setEmail(user.getEmail());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(pwdEncoder.encode(user.getPassword()));

        User savedUser = userRepo.save(createdUser);

        // after successfully creating the user, we need to create the cart for that user
        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepo.save(cart);

        // creating the authentication and generating the token
        // Constructor: new UsernamePasswordAuthenticationToken(Object principal, Object credentials)
        //principal: Typically the username or email.
        //credentials: The password.
        //authorities (optional): A collection of granted authorities or roles.
        //The UsernamePasswordAuthenticationToken class in Spring Security is a convenient way to create an Authentication object for username and password-based authentication. This object can be used to authenticate a user in a Spring Security context.
        Authentication auths = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auths);
        String jwt = jwtProvider.generatedToken(auths);

        AuthResponse authRes = new AuthResponse();
        authRes.setJwt(jwt);
        authRes.setMessage("User Created Successfully");
        authRes.setRole(savedUser.getRole());


        return new ResponseEntity<>(authRes, HttpStatus.CREATED);

    }


    /// LOGIN FUNCTION

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest req){

        String email = req.getEmail();
        String password = req.getPassword();

        // checking for the authentication here via another function
        Authentication auths = authentication(email, password);

        // getting the authorities of the users
        Collection<? extends GrantedAuthority> authorities = auths.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        // if the auth is valid then generate the token

        String jwt = jwtProvider.generatedToken(auths);

        AuthResponse authRes = new AuthResponse();
        authRes.setJwt(jwt);
        authRes.setMessage("User Login Successful");
        // getting the roles of the users

        authRes.setRole(User_Role.valueOf(role));


        return new ResponseEntity<>(authRes, HttpStatus.OK);



    }

    private Authentication authentication(String email, String password) {

        // user details comes from spring security

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

       if(userDetails == null){
           throw new BadCredentialsException("Invalid email");
       }

       // checking if the password matches

        if(!pwdEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}

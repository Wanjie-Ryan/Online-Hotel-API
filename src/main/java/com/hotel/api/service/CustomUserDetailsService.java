package com.hotel.api.service;

// stopping auto generating passwords brought by spring security
// the UserDetailsService comes from spring security package itself.
import com.hotel.api.model.User;
import com.hotel.api.model.User_Role;
import com.hotel.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("User not found with email " +user);

        }

        // if user is available, get the role of the user

        User_Role role = user.getRole();

        // check if role is null, if it is, then assign the role of customer to the user

        if(role == null) role = User_Role.Customer;

        List<GrantedAuthority> auth = new ArrayList<>();

        auth.add(new SimpleGrantedAuthority(role.toString()));


        return null;
    }
}

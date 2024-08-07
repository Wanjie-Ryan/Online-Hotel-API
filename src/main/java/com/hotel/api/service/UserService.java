package com.hotel.api.service;

import com.hotel.api.config.JwtProvider;
import com.hotel.api.model.User;
import com.hotel.api.repository.UserJwtRepository;
import com.hotel.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserJwtRepository {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwt(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepo.findByEmail(email);
        if(user == null){
            throw new Exception("user with the email not found");
        }


        return user;
    }
}

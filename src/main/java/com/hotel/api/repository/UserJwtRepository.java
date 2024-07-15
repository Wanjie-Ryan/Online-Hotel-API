package com.hotel.api.repository;

import com.hotel.api.model.User;

public interface UserJwtRepository {

    // custom method to find the user by the token
    public User findUserByJwt(String jwt) throws Exception;

    public User findUserByEmail (String email) throws Exception;
}

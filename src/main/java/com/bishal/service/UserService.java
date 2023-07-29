package com.bishal.service;

import com.bishal.exception.UserException;
import com.bishal.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;
    public User findUserProfileByJwt(String jwt) throws UserException;
}

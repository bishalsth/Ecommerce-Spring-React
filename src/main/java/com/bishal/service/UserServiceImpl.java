package com.bishal.service;

import com.bishal.config.JwtProvider;
import com.bishal.exception.UserException;
import com.bishal.model.User;
import com.bishal.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserRepo userRepo, JwtProvider jwtProvider) {
        this.userRepo = userRepo;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> opt = userRepo.findById(userId);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new UserException("user not found with id: "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {

        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepo.findByEmail(email);

        if(user==null){
            throw new UserException("user not found with email: "+ email);
        }
        return user;
    }

}

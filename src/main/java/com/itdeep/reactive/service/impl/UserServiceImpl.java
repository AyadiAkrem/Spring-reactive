package com.itdeep.reactive.service.impl;

import com.itdeep.reactive.entities.User;
import com.itdeep.reactive.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return User.class.cast(principal);
        }
        return null;
    }
}

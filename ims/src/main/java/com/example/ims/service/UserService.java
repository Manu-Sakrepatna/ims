package com.example.ims.service;

import com.example.ims.models.Product;
import com.example.ims.models.User;
import com.example.ims.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User fetchUserByUserID(String userid){
        User user = userRepository.findUserByUserid(userid);
        return user;
    }
}

package com.mediumBlog.Flowabledemo.serviceImpl;

import com.mediumBlog.Flowabledemo.entity.User;
import com.mediumBlog.Flowabledemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public User saveUser(User user) {
        if (user==null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        return userRepository.save(user);
    }

}

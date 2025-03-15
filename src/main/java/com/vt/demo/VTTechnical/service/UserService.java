package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.UserRepository;
import com.vt.demo.VTTechnical.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    GeminiService geminiService;

    public User createUser(@Validated User user) {
            return userRepository.save(user);
    }

    public List<User> getUser(String email) {

        return userRepository.findByEmail(email);
    }
}

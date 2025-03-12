package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.UserRepository;
import com.vt.demo.VTTechnical.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUser(String email) {

        List<User> userList = userRepository.findByEmail(email);
        userList.forEach(System.out::println);
        return userRepository.findByEmail(email);


    }
}

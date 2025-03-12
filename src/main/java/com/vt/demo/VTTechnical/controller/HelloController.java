package com.vt.demo.VTTechnical.controller;

import com.vt.demo.VTTechnical.model.User;
import com.vt.demo.VTTechnical.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public List<User> getUser(@PathVariable String email) {
        return userService.getUser(email);
    }
}

package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.TeamRepository;
import com.vt.demo.VTTechnical.dao.UserRepository;
import com.vt.demo.VTTechnical.model.Team;
import com.vt.demo.VTTechnical.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Sql(scripts = "/create-schema.sql")
public class UserServiceTests {

    public static final String TEST_EMAIL_FAKE_COM = "TestEmail@fake.com";
    @Autowired
    UserService userService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateUser() {

        List<Team> teams = teamRepository.findByTeamName("Team1");
        userService.createUser(new User(TEST_EMAIL_FAKE_COM, teams));
        assertNotNull(userRepository.findByEmail(TEST_EMAIL_FAKE_COM).getFirst());
    }
}

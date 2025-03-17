package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.model.Team;
import com.vt.demo.VTTechnical.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTests extends BaseServiceTest {

    public static final String TEST_EMAIL_FAKE_COM = "TestEmail@fake.com";
    public static final String JOHN_DOE_2_EXAMPLE_COM = "john.doe2@example.com";

    @Test
    public void testCreateUser() {
        Team team = teamRepository.findByTeamName("Team1");
        userService.createOrUpdateUser(new User(TEST_EMAIL_FAKE_COM, List.of(team)));
        assertNotNull(userRepository.findByEmail(TEST_EMAIL_FAKE_COM));
    }

    @Test
    public void testAssignUserToTeam(){
        User testUser = userService.getUser(JOHN_DOE_2_EXAMPLE_COM);
        assertEquals(1,testUser.getTeams().size());

        userService.assignUserToTeam(JOHN_DOE_2_EXAMPLE_COM,"Team2");

        testUser = userService.getUser(JOHN_DOE_2_EXAMPLE_COM);
        assertEquals(2,testUser.getTeams().size());

    }
}

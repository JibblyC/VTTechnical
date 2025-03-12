package com.vt.demo.VTTechnical.dao;


import com.vt.demo.VTTechnical.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Sql(scripts = "/create-schema.sql")
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        List<User> foundUser = userRepository.findByEmail("john.doe@example.com");
        assertEquals(1,foundUser.size());
    }

    @Test
    public void testFindByDateGreaterThen() {
        LocalDateTime startDate = LocalDateTime.parse("2025-03-01T08:45:00");
        LocalDateTime endDate = LocalDateTime.parse("2025-03-31T08:45:00");
        List<User> foundUser = userRepository.findUsersWithNoUploads(Timestamp.valueOf(startDate),Timestamp.valueOf(endDate));
        assertEquals(1,foundUser.size());
    }
}
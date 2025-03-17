package com.vt.demo.VTTechnical.dao;


import com.vt.demo.VTTechnical.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@SpringBootTest
@Sql(scripts = "/create-schema.sql", executionPhase = BEFORE_TEST_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByDateGreaterThen() {
        LocalDateTime startDate = LocalDateTime.parse("2025-03-01T08:45:00");
        LocalDateTime endDate = LocalDateTime.parse("2025-03-31T08:45:00");
        List<User> foundUser = userRepository.findUsersWithNoUploads(Timestamp.valueOf(startDate),Timestamp.valueOf(endDate));
        assertEquals(1,foundUser.size());
    }
}
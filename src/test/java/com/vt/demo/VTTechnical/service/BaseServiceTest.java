package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.DocumentRepository;
import com.vt.demo.VTTechnical.dao.TeamRepository;
import com.vt.demo.VTTechnical.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@SpringBootTest
@Sql(scripts = "/create-schema.sql", executionPhase = BEFORE_TEST_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class BaseServiceTest {

    @Autowired
    DocumentService documentService;

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    UserRepository userRepository;
}

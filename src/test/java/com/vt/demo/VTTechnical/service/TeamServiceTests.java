package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.TeamRepository;
import com.vt.demo.VTTechnical.model.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;


@SpringBootTest
@Sql(scripts = "/create-schema.sql" ,executionPhase = BEFORE_TEST_CLASS)
public class TeamServiceTests {

    @Autowired
    TeamService teamService;

    @Autowired
    TeamRepository teamRepository;

    @Test
    public void teamCreateNewTeam(){

        Team createdTeam = teamService.createTeam("Team3");

        assertNotNull(teamRepository.findByTeamName("Team3").getFirst());

    }
}

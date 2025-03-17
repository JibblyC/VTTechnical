package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.model.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TeamServiceTests extends BaseServiceTest {

    @Test
    public void teamCreateNewTeam() {
        Team createdTeam = teamService.createTeam("Team3");
        assertNotNull(teamRepository.findByTeamName("Team3"));

    }
}

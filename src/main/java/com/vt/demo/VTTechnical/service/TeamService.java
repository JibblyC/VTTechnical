package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.TeamRepository;
import com.vt.demo.VTTechnical.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Team createTeam(String teamName){
        return teamRepository.save(new Team(teamName));
    }
}

package com.vt.demo.VTTechnical.dao;

import com.vt.demo.VTTechnical.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByTeamName(String teamName);
}

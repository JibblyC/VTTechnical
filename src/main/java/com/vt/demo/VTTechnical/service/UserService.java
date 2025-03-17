package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.UserRepository;
import com.vt.demo.VTTechnical.model.Team;
import com.vt.demo.VTTechnical.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamService teamService;

    public User createOrUpdateUser(User user) {
            return userRepository.save(user);
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public void assignUserToTeam(String email, String teamName){
        User user = userRepository.findByEmail(email);
        Team team = teamService.findByTeamName(teamName);
        user.addTeam(team);
        team.addUser(user);
        userRepository.save(user);
    }

    public List<User> getListOfUsersWithNoUploadsBetweenDates(Timestamp startDate, Timestamp endDate){
        return userRepository.findUsersWithNoUploads(startDate,endDate);
    }
}

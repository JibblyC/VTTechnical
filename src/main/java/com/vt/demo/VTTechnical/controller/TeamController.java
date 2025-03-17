package com.vt.demo.VTTechnical.controller;


import com.vt.demo.VTTechnical.service.TeamService;
import com.vt.demo.VTTechnical.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Object> createTeam(@RequestParam String teamName){
        return ResponseEntity.ok(String.format("Team %s created successfully.",teamService.createTeam(teamName).getTeamName()));
    }

    @PostMapping("/assign")
    public ResponseEntity<Object> assignTeam(@RequestParam String teamName,@RequestParam String email){

        if (teamService.findByTeamName(teamName) == null) {
            return new ResponseEntity<>("Team Name is not valid", HttpStatus.BAD_REQUEST);
        }
        if (userService.getUser(email) == null) {
            return new ResponseEntity<>("User is not valid", HttpStatus.BAD_REQUEST);
        }
        userService.assignUserToTeam(email,teamName);
        return ResponseEntity.ok("User successfully added to team.");
    }
}

package com.vt.demo.VTTechnical.controller;

import com.vt.demo.VTTechnical.model.Team;
import com.vt.demo.VTTechnical.model.User;
import com.vt.demo.VTTechnical.service.TeamService;
import com.vt.demo.VTTechnical.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestParam String email,@RequestParam String teamName){
        Team team = teamService.findByTeamName(teamName);
        if(team == null){
            return ResponseEntity.badRequest().body(String.format("Team %s has not been registered with the system, please create team first", teamName));
        }
        return ResponseEntity.ok(userService.createOrUpdateUser(new User(email, List.of(team))));
    }

    @GetMapping("/nouploads")
    public ResponseEntity<Object> getNoUploads(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") String startDate, @DateTimeFormat(pattern = "dd-MM-yyyy") String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedStartDate = sdf.parse(startDate);
        Date parsedEndDate = sdf.parse(endDate);

        // Convert Date to Timestamp
        Timestamp startDateTimestamp = new Timestamp(parsedStartDate.getTime());
        Timestamp endDateTimestamp = new Timestamp(parsedEndDate.getTime());

        return ResponseEntity.ok(userService.getListOfUsersWithNoUploadsBetweenDates(startDateTimestamp,endDateTimestamp));
    }


}

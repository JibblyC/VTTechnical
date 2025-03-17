package com.vt.demo.VTTechnical.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "teams", uniqueConstraints = @UniqueConstraint(columnNames = "team_name"))
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId;

    private String teamName;

    @JsonIgnore
    @ManyToMany(mappedBy = "teams", fetch = FetchType.EAGER)
    private Set<User> users;

    public void addUser(User user){
        users.add(user);
    }

    public Team() {
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}


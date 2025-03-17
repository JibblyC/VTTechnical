package com.vt.demo.VTTechnical.model;


import jakarta.persistence.*;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, max = 50, message = "Email must be between 2 and 50 characters")
    private String email;

    private Timestamp dateJoined;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Document> documents;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_teams",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;

    public User() {
    }

    public User(String email, List<Team> teams) {
        this.email = email;
        this.teams = teams;
        this.dateJoined = new Timestamp(System.currentTimeMillis());
    }

    public void addTeam(Team team){
        teams.add(team);
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", dateJoined=" + dateJoined +
                ", documents=" + documents +
                '}';
    }
}

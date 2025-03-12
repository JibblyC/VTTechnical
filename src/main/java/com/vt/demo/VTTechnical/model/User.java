package com.vt.demo.VTTechnical.model;


import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    private Timestamp dateJoined;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documents;

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

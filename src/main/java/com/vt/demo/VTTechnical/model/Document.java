package com.vt.demo.VTTechnical.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    private String documentName;

    private Long wordCount;

    private Timestamp dateAdded;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id") // The foreign key column
    User user;


    public Document(String documentName, Long wordCount, User user) {
        this.documentName = documentName;
        this.wordCount = wordCount;
        this.user = user;
        this.dateAdded = new Timestamp(System.currentTimeMillis());
    }

    public Document() {
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public Long getWordCount() {
        return wordCount;
    }

    public void setWordCount(Long wordCount) {
        this.wordCount = wordCount;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.vt.demo.VTTechnical.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    private String documentName;

    private String documentText;

    private String wordCount;

    private Timestamp dateAdded;

    @ManyToOne
    @JoinColumn(name = "user_id") // The foreign key column
    User user;

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

    public String getDocumentText() {
        return documentText;
    }

    public void setDocumentText(String documentText) {
        this.documentText = documentText;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
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

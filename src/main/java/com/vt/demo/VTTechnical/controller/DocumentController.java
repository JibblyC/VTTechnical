package com.vt.demo.VTTechnical.controller;

import com.vt.demo.VTTechnical.model.Document;
import com.vt.demo.VTTechnical.model.User;
import com.vt.demo.VTTechnical.service.DocumentService;
import com.vt.demo.VTTechnical.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("document") MultipartFile document, @RequestParam("useremail") String userEmail) {
        if (document.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected");
        }
        if (userEmail.isEmpty()) {
            return ResponseEntity.badRequest().body("Please enter a valid email");
        }
        User currentUser = userService.getUser(userEmail).getFirst();
        if (currentUser == null) {
            return ResponseEntity.badRequest().body(String.format("User %s has not been registered with the system, please register or choose another user", userEmail));
        }
        try {

            Document savedDocument = documentService.createNewDocument(document, currentUser);
            return ResponseEntity.ok(String.format("Document with id %s uploaded successfully", savedDocument.getDocumentId()));
        } catch (IOException ex) {
            return ResponseEntity.status(500).body("Error uploading file: " + ex.getMessage());
        }
    }

    @GetMapping("/topwords/{documentId}")
    public ResponseEntity<Object> uploadFile(@PathVariable("documentId") long docuemntID) throws IOException {
        Optional<Document> savedDocument = documentService.getDocumentByDocumentId(docuemntID);
        if (savedDocument.isEmpty()) {
            return new ResponseEntity<>("Document ID is not valid", HttpStatus.BAD_REQUEST);
        }
        Map<String, Integer> topTen = documentService.getTopTenWordCount(savedDocument.get());
        return new ResponseEntity<>(topTen, HttpStatus.OK);

    }
}

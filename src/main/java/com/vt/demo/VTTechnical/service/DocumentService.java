package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.DocumentRepository;
import com.vt.demo.VTTechnical.model.Document;
import com.vt.demo.VTTechnical.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    UserService userService;


    public Document createDocument(Document document){
        return documentRepository.save(document);
    }

    public Document getDocumentByDocumentId(long documentId){
        return documentRepository.getReferenceById(documentId);
    }

    public Document createNewDocument(MultipartFile uploadedFile,User currentUser) throws IOException {
        String filename = StringUtils.cleanPath(uploadedFile.getOriginalFilename() + UUID.randomUUID());
        Path targetLocation = Paths.get(uploadDir).resolve(filename);
        Files.copy(uploadedFile.getInputStream(), targetLocation);
        long wordCount = countWordsInUploadedFile(uploadedFile);
        return documentRepository.save(new Document(filename,wordCount,currentUser));
    }

    private long countWordsInUploadedFile(MultipartFile uploadedFile) throws IOException {
        // Get input stream of the file
        InputStream inputStream = uploadedFile.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        long wordCount = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            wordCount += line.split("\\s+").length;
        }
        return wordCount;
    }
}

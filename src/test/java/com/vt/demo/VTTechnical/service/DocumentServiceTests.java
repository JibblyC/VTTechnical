package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.model.Document;
import com.vt.demo.VTTechnical.model.User;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentServiceTests extends BaseServiceTest{

    @Value("${document.upload.directory}")
    private String uploadDir;

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(Paths.get(uploadDir));
    }

    @Test
    public void testCreateDocument() throws IOException {
        MockMultipartFile mockMultiPartFile = new MockMultipartFile(
                "file",
                "testdocument.txt",
                "text/plain",
                "This is a test document.".getBytes()
        );

        User user = userService.getUser("john.doe@example.com");
        Document documentResponse = documentService.createNewDocument(mockMultiPartFile,user);
        assertEquals(5,documentResponse.getWordCount());
    }

    @Test
    public void testTopTenWords() throws IOException {
        Optional<Document> testDocument = documentRepository.findById(6L);
        Map<String, Integer> topTenWords = documentService.getTopTenWordCount(testDocument.get());
        assertEquals(10,topTenWords.size());


        Map.Entry<String,Integer> topEntry = topTenWords.entrySet().iterator().next();
        assertEquals("DRY",topEntry.getKey());
        assertEquals(13,topEntry.getValue());
    }

    @Test
    public void testGetLongestWord() throws IOException {
        Optional<Document> testDocument = documentRepository.findById(6L);
        String longestWord = documentService.getLongestWordInDocument(testDocument.get());
        assertEquals("ABUNDANTLY",longestWord);


    }

}

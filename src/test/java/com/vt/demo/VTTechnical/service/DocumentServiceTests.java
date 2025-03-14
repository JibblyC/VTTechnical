package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.DocumentRepository;
import com.vt.demo.VTTechnical.model.Document;
import com.vt.demo.VTTechnical.model.User;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@SpringBootTest
@Sql(scripts = "/create-schema.sql", executionPhase = BEFORE_TEST_CLASS)
public class DocumentServiceTests {

    @Value("${document.upload.directory}")
    private String uploadDir;

    @Autowired
    DocumentService documentService;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    UserService userService;

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

        User user = userService.getUser("john.doe@example.com").getFirst();
        Document documentResponse = documentService.createNewDocument(mockMultiPartFile,user);
        assertEquals(5,documentResponse.getWordCount());
    }

    @Test
    public void testTopTenWords() throws IOException {
        Optional<Document> testDocument = documentRepository.findById(6L);
        Map<String, Integer> topTenWords = documentService.getTopTenWordCount(testDocument.get());
        assertEquals(10,topTenWords.size());
    }

}

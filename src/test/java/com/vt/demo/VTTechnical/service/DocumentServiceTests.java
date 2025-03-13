package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.DocumentRepository;
import com.vt.demo.VTTechnical.model.Document;
import com.vt.demo.VTTechnical.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(scripts = "/create-schema.sql")
public class DocumentServiceTests {

    @Autowired
    DocumentService documentService;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    UserService userService;

    @Test
    public void testCreateDocument(){
        User user = userService.getUser("john.doe@example.com").getFirst();

        Document document = new Document("DocName",2L,user);
        Document documentResponse = documentService.createDocument(document);
        assertNotNull(documentRepository.findById(documentResponse.getDocumentId()));
    }

}

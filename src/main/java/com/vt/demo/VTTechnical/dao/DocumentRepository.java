package com.vt.demo.VTTechnical.dao;

import com.vt.demo.VTTechnical.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}

package com.vt.demo.VTTechnical;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class VtTechnicalApplication {

	@Value("${file.upload-dir}")
	private String uploadDir;

	public static void main(String[] args) {
		SpringApplication.run(VtTechnicalApplication.class, args);
	}

	@PostConstruct
	public void postConstruct() throws IOException {
		Files.createDirectories(Paths.get(uploadDir));
	}

}

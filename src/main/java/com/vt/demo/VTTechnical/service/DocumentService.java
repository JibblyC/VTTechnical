package com.vt.demo.VTTechnical.service;

import com.vt.demo.VTTechnical.dao.DocumentRepository;
import com.vt.demo.VTTechnical.model.Document;
import com.vt.demo.VTTechnical.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DocumentService {

    public static final String REGEX_WORD_PATTERN = "\\b[a-zA-Z0-9'@.-]+\\b";

    @Autowired
    DocumentRepository documentRepository;

    @Value("${document.upload.directory}")
    private String uploadDir;

    @Value("${document.ignore.regex.pattern}")
    private String ignoreWordListRegexPattern;


    public Optional<Document> getDocumentByDocumentId(long documentId){
        return documentRepository.findById(documentId);
    }

    public Document createNewDocument(MultipartFile uploadedFile,User currentUser) throws IOException {
        String filename = StringUtils.cleanPath(uploadedFile.getOriginalFilename() +"_"+ UUID.randomUUID());
        Path targetLocation = Paths.get(uploadDir).resolve(filename);
        Files.copy(uploadedFile.getInputStream(), targetLocation);
        long wordCount = countWordsInUploadedFile(uploadedFile);
        return documentRepository.save(new Document(filename,wordCount,currentUser));
    }

    public Map<String, Integer> getTopTenWordCount(Document document) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(uploadDir +"/"+ document.getDocumentName()));
        Map<String, Integer> wordCountMap = new HashMap<>();
        String line;
        Pattern wordPattern = Pattern.compile(ignoreWordListRegexPattern+REGEX_WORD_PATTERN,Pattern.CASE_INSENSITIVE);

        while ((line = reader.readLine()) != null) {
            Matcher matcher = wordPattern.matcher(line);
            while(matcher.find()){
                String word = matcher.group().toUpperCase();
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }

        //Convert to list and sort
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordCountMap.entrySet());
        wordList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        //Covert back to Map and take top ten -- overkill ?
        Map<String, Integer> topTenMap = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(10, wordList.size()); i++) {
            Map.Entry<String, Integer> entry = wordList.get(i);
            topTenMap.put(entry.getKey(), entry.getValue());
        }
        return topTenMap;
    }

    public String getLongestWordInDocument(Document document) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(uploadDir +"/"+ document.getDocumentName()));
        Pattern wordPattern = Pattern.compile(REGEX_WORD_PATTERN);
        String line;
        String longestWord = "";
        while ((line = reader.readLine()) != null) {
            Matcher matcher = wordPattern.matcher(line);
            while(matcher.find()){
                String word = matcher.group().toUpperCase();
                if(word.length() > longestWord.length()){
                    longestWord = word;
                }
            }
        }
    return longestWord;
    }


    private long countWordsInUploadedFile(MultipartFile uploadedFile) throws IOException {
        // Get input stream of the file
        InputStream inputStream = uploadedFile.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Pattern wordPattern = Pattern.compile(REGEX_WORD_PATTERN);
        long wordCount = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher matcher = wordPattern.matcher(line);
            while (matcher.find()) {
                wordCount++;
            }
        }
        return wordCount;
    }
}

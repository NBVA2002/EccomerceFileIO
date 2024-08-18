package com.example.fileservice.service;

import com.example.fileservice.dto.ResponeFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileStorageService {
    List<ResponeFile> storeFile(String path, List<MultipartFile> file);

    Stream<Path> loadAll();

    InputStreamResource readFileContent(String path, String fileName);

    void deleteFile(String path, String fileName);
}

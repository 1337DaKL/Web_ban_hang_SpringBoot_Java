/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.services;

import java.nio.file.Path;
import java.nio.file.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import org.springframework.stereotype.Service;
/**
 *
 * @author luong
 */
@Service
public class FileSystemStorageService implements StorageService{
    private final Path rootLocation;

    public FileSystemStorageService() {
        this.rootLocation = Paths.get("project/src/main/resources/static/uploads");
        init();
    }
    
    @Override
    public void store(MultipartFile file) {
        try {
            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(file.getOriginalFilename())
            ).normalize().toAbsolutePath();
            try(InputStream inputStream = file.getInputStream())
            {
                Files.copy(inputStream, destinationFile , StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (Exception e) {
        }
    }
    
}

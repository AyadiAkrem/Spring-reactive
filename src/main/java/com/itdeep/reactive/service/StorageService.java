package com.itdeep.reactive.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;


public interface StorageService {


    void store(MultipartFile file, String root);


    Stream<Path> loadAll(String folderName);
}

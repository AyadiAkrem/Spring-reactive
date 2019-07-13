package com.itdeep.reactive.service.impl;

import com.itdeep.reactive.config.StorageProperties;
import com.itdeep.reactive.exceptions.StorageException;
import com.itdeep.reactive.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Stream;


@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        ;
    }


    @Override
    public void store(MultipartFile file, String root) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(root + File.separatorChar + filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

    }

    @Override
    public Stream<Path> loadAll(String folderName) {
        try {
            final Path resolve = this.rootLocation.resolve(folderName);
            if (resolve.toFile().exists()) {
                return Files.walk(resolve, 1)
                        .filter(path -> !path.equals(resolve))
                        .map(this.rootLocation::relativize);
            } else {
                return Arrays.stream(new Path[0]);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

}

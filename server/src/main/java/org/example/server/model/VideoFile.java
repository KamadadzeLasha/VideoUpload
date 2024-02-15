package org.example.server.model;

import lombok.Data;
import org.example.server.Properties.StorageProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Represents a video file uploaded by the user.
 * unwrapping Spring boot Multipart file directly
 * File could be Fetched from database instead of temporarily stored locally
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
@Data
public class VideoFile {
    private String name;
    private Path inputFilePath;
    private long size;

    /**
     * Constructs a VideoFile object from the provided MultipartFile.
     *
     * @param multipartFile The MultipartFile representing the uploaded video file.
     * @throws RuntimeException If an error occurs while creating the VideoFile.
     */
    public VideoFile(MultipartFile multipartFile) {
        try {
            this.name = multipartFile.getOriginalFilename();
            this.inputFilePath = StorageProperties.TEMPLOCATION.resolve(Objects.requireNonNull(this.name)).toAbsolutePath();
            multipartFile.transferTo(inputFilePath);
            this.size = multipartFile.getSize();
        } catch (IOException e) {
            throw new RuntimeException("Unable to create Video File: " + this.name + e);
        }
    }
}

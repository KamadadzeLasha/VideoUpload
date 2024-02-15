package org.example.server.service;

import org.example.server.model.FileInfo;
import org.example.server.model.VideoConversionRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * This interface represents a service for handling multimedia file conversion operations.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
public interface FileService {

    /**
     * Initializes the file conversion service.
     * This method should be called before performing any file conversion operations.
     */
    void initialize();

    /**
     * Converts the multimedia file based on the provided conversion request.
     *
     * @param conversionRequest The request containing the details for the file conversion.
     * @return Information about the converted file, including its name, URL, and additional metadata.
     * @throws IOException If an IO error occurs during the conversion process.
     */
    FileInfo convertFile(VideoConversionRequest conversionRequest) throws IOException;

    /**
     * Saves the uploaded file.
     *
     * @param file The multipart file to save.
     */
    void save(MultipartFile file);

    /**
     * Deletes all files stored by the service.
     *
     * @throws IOException If an IO error occurs during the deletion process.
     */
    void deleteAll() throws IOException;

    /**
     * Loads all stored files.
     *
     * @return A stream of paths to the stored files.
     */
    Stream<Path> loadAll();

    /**
     * Loads the file with the specified filename.
     *
     * @param filename The name of the file to load.
     * @return A Resource representing the loaded file.
     */
    Resource load(String filename);
}

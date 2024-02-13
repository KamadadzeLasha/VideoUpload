package org.example.server.service;

import org.example.server.model.ConvertedFileInfo;
import org.example.server.model.VideoConversionRequest;

/**
 * This interface represents a service for handling multimedia file conversion operations.
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
     */
    ConvertedFileInfo convertFile(VideoConversionRequest conversionRequest);

    void deleteAll();
    

    /**
     * Loads the file url based on their name.
     *
     * @param fileName The file name.
     * @return The converted file information.
     * 
     *  Note: Method could be used later.
     * 
     */
    // ConvertedFileInfo load(String fileName);
}


package org.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.core.io.Resource;

/**
 * Represents information about an already converted file.
 */
@Data
@AllArgsConstructor
public class ConvertedFileInfo {
    /**
     * The name of the converted file.
     */
    private final String fileName;

    /**
     * The URL of the converted file.
     */
    private final String fileUrl;

    /**
     * Additional information about the converted file.
     */
//    private final String fileMetaData;
}
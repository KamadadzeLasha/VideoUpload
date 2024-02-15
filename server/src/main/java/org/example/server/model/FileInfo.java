package org.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.URI;

/**
 * Represents information about an already converted file.
 */
@Data
@AllArgsConstructor
public class FileInfo {
    /**
     * The name of the converted file.
     */
    private String name;

    /**
     * The URL of the converted file.
     */
    private URI fileUrl;
}

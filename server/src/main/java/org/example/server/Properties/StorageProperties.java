package org.example.server.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Configuration properties for file storage.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    /**
     * Folder location for storing uploaded files.
     */
    public static Path ROOTLOCATION = Paths.get("upload-dir");

    /**
     * Temporary folder location for storing temporary files.
     */
    public static Path TEMPLOCATION = Paths.get("temp-dir");
}

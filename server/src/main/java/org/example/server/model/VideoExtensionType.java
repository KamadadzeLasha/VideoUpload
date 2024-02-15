package org.example.server.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum representing various video file extensions.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
@Getter
public enum VideoExtensionType {
    MP4(".mp4"),
    AVI(".avi"),
    MKV(".mkv"),
    MOV(".mov"),
    WMV(".wmv"),
    FLV(".flv"),
    WEBM(".webm"),
    MPEG(".mpeg"),
    MPG(".mpg"),
    M4V(".m4v"),
    TS(".ts"),
    VOB(".vob"),
    _3GP(".3gp"),
    OGG(".ogg"),

    // Placeholder for unknown or unsupported extensions
    UNKNOWN("");

    private final String extension;

    private static final Map<String, VideoExtensionType> extensionMap = new HashMap<>();

    static {
        for (VideoExtensionType type : VideoExtensionType.values()) {
            extensionMap.put(type.extension, type);
        }
    }

    /**
     * Constructs a VideoExtensionType enum with the given extension.
     * @param extension The file extension.
     */
    VideoExtensionType(String extension) {
        this.extension = extension;
    }

    /**
     * Retrieves the VideoExtensionType enum corresponding to the provided extension string.
     * @param extension The file extension.
     * @return The VideoExtensionType enum corresponding to the extension, or UNKNOWN if not found.
     */
    public static VideoExtensionType fromString(String extension) {
        return extensionMap.getOrDefault(extension, UNKNOWN);
    }

    /**
     * Checks if the provided extension is a valid video file extension.
     * @param extension The file extension.
     * @return true if the extension is valid, false otherwise.
     */
    public static boolean isValidExtension(String extension) {
        if (extension == null){
            return false;
        }
        return extensionMap.containsKey(extension.toLowerCase());
    }
}

package org.example.server.model;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Represents a video conversion request containing details for converting a video file.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
@Data
public class VideoConversionRequest {
    /**
     * The video file to be converted.
     */
    @Nonnull
    private VideoFile videoFile;

    /**
     * The video Extension Type  for the converted video file.
     */
    private String videoExtensionType;

    /**
     * The frame rate of the converted video.
     */
    @Nullable
    private Long frameRate;

    /**
     * The width of the converted video.
     */
    @Nullable
    private Long width;

    /**
     * The height of the converted video.
     */
    @Nullable
    private Long height;

    /**
     * Constructs a VideoConversionRequest object with the specified parameters.
     *
     * @param multipartFile      The multipart file representing the video to be converted.
     * @param videoExtensionType The extension type for the converted video file.
     * @param frameRate          The frame rate of the converted video.
     * @param width              The width of the converted video.
     * @param height             The height of the converted video.
     */
    public VideoConversionRequest(@Nonnull MultipartFile multipartFile, @Nullable String videoExtensionType, long frameRate, long width, long height) {
        this.videoFile = new VideoFile(multipartFile);
        this.videoExtensionType = VideoExtensionType.isValidExtension(videoExtensionType) ? videoExtensionType : ".mp4";  // default to MP4 if not provided
        this.frameRate = frameRate;
        this.width = width;
        this.height = height;
    }
}

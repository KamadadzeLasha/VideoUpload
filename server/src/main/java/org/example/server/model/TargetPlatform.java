package org.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TargetPlatform {
    FACEBOOK("mp4", 1280, 720),
    INSTAGRAM("mp4", 1080, 1920),
    TWITTER("mp4", 1280, 720),
    LINKEDIN("mp4", 1280, 720),
    TIKTOK("mp4", 1080, 1920),
    SNAPCHAT("mp4", 1080, 1920);
    // Add more social media platforms as needed

    private final String extensionType;
    private final int videoWidth;
    private final int videoHeight;

    @Override
    public String toString() {
        return "extensionType=" + extensionType + ", videoWidth=" + videoWidth + ", videoHeight=" + videoHeight;
    }
}

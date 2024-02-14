package org.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TargetPlatform {
    FACEBOOK("mp4", "1280x720"),
    INSTAGRAM("mp4", "1080x1920"),
    TWITTER("mp4", "1280x720"),
    LINKEDIN("mp4", "1280x720"),
    TIKTOK("mp4", "1080x1920"),
    SNAPCHAT("mp4", "1080x 1920");
    // Add more social media platforms as needed

    private final String extensionType;
    private final String videoSize;

    @Override
    public String toString() {
        return "extensionType=" + extensionType + ", videoSize=" + videoSize ;
    }
}

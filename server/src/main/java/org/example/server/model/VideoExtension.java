package org.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VideoExtension {
    MP4("mp4"),
    AVI("avi"),
    MKV("mkv"),
    MOV("mov"),
    WMV("wmv"),
    FLV("flv"),
    WEBM("webm"),
    _3GP("3gp"),
    _3G2("3g2"),
    OGG("ogg"),
    MPG("mpg"),
    MPEG("mpeg"),
    TS("ts"),
    VOB("vob"),
    DIVX("divx"),
    H264("h264"),
    H265("hevc"),
    VP9("vp9");

    private final String extension;

}


package org.example.server.utils;

import java.util.Objects;

public class FFmpegRunner {
    private final CommandRunner commandRunner;

    public FFmpegRunner() {
        this.commandRunner = new CommandRunner();
//        String a = Objects.requireNonNull(getClass().getResource("/binaries/ffmpeg")).getFile();
        String a = "ffmpeg";
        System.out.println(a);
        commandRunner.addArgument(a);
    }

    public FFmpegRunner addInput(String inputPath) {
        return handleArguments("-i", inputPath);
    }

    public FFmpegRunner addOutput(String outputPath) {
        return handleArguments(outputPath);
    }

    public FFmpegRunner setVideoSize(String size) {
        return handleArguments("-vf", "scale=" + size);
    }

    public FFmpegRunner setCodec(String codecType) {
        return handleArguments("-c:v", codecType);
    }

    public FFmpegRunner setBitrate(String bitrate) {
        return handleArguments("-b:v", bitrate);
    }

    public FFmpegRunner setOutputFormat(String format) {
        return handleArguments("-f", format);
    }

    public FFmpegRunner setAudioCodec(String codecType) {
        return handleArguments("-c:a", codecType);
    }

    public FFmpegRunner setAudioBitrate(String bitrate) {
        return handleArguments("-b:a", bitrate);
    }

    public FFmpegRunner setDuration(String duration) {
        return handleArguments("-t", duration);
    }

    public FFmpegRunner setAspectRatio(String ratio) {
        return handleArguments("-aspect", ratio);
    }

    public FFmpegRunner setFramerate(String framerate) {
        return handleArguments("-r", framerate);
    }

    public FFmpegRunner addMetadata(String key, String value) {
        return handleArguments("-metadata", key + "=" + value);
    }

    public FFmpegRunner setPreset(String preset) {
        return handleArguments("-preset", preset);
    }

    public FFmpegRunner addFilter(String filter) {
        return handleArguments("-filter", filter);
    }

    private FFmpegRunner handleArguments(String... arguments) {
        for (String arg : arguments) {
            commandRunner.addArgument(arg);
        }
        return this;
    }

    public boolean run(boolean logOutput) {
        return commandRunner.run(logOutput);
    }

    public static void main(String[] args) {
        FFmpegRunner runner = new FFmpegRunner();
        runner.addInput("/home/don/Videos/video1.mp4");
        runner.addOutput("/home/don/Videos/sample.mp4");

        System.out.println(runner.run(true));
    }
}

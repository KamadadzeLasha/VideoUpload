package org.example.server.utils;

import java.util.Objects;

public class FFmpegRunner {
    private final CommandRunner commandRunner;

    public FFmpegRunner() {
        this.commandRunner = new CommandRunner();
        String pathToFFmpegBinary = Objects.requireNonNull(getClass().getResource("/binaries/ffmpeg")).getFile();
        commandRunner.addArgument(pathToFFmpegBinary);
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
    public FFmpegRunner addComplexFilter(String filter) {
        return handleArguments("-lavfi", filter);
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
}

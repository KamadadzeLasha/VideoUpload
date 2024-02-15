package org.example.server.utils;

import java.nio.file.Path;

/**
 * Utility class to run FFmpeg commands.
 * *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
public class FFmpegRunner {
    private final CommandRunner commandRunner;

    /**
     * Constructor to initialize the FFmpegRunner with default settings.
     */
    public FFmpegRunner() {
        this.commandRunner = new CommandRunner();
        commandRunner.addExecutable("binaries/ffmpeg");
    }

    /**
     * Adds input file path to the FFmpeg command.
     *
     * @param inputPath The path of the input file.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner addInput(Path inputPath) {
        return handleArguments("-i", inputPath.toString());
    }

    /**
     * Adds output file path to the FFmpeg command.
     *
     * @param outputPath The path of the output file.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner addOutput(Path outputPath) {
        return handleArguments(outputPath.toString());
    }

    public FFmpegRunner setVideoSize(long width, long height) {
        return handleArguments("-vf", "scale=%d:%d".formatted(width, height));
    }

    /**
     * Sets the codec type for the video in the FFmpeg command.
     *
     * @param codecType The codec type.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner setCodec(String codecType) {
        return handleArguments("-c:v", codecType);
    }

    /**
     * Sets the bitrate for the video in the FFmpeg command.
     *
     * @param bitrate The bitrate value.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner setBitrate(String bitrate) {
        return handleArguments("-b:v", bitrate);
    }

    /**
     * Sets the output format for the FFmpeg command.
     *
     * @param format The output format.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner setOutputFormat(String format) {
        return handleArguments("-f", format);
    }

    /**
     * Sets the audio codec type for the audio in the FFmpeg command.
     *
     * @param codecType The audio codec type.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner setAudioCodec(String codecType) {
        return handleArguments("-c:a", codecType);
    }

    /**
     * Sets the audio bitrate for the audio in the FFmpeg command.
     *
     * @param bitrate The audio bitrate value.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner setAudioBitrate(String bitrate) {
        return handleArguments("-b:a", bitrate);
    }

    /**
     * Sets the duration for the FFmpeg command.
     *
     * @param duration The duration value.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner setDuration(String duration) {
        return handleArguments("-t", duration);
    }

    /**
     * Sets the aspect ratio for the FFmpeg command.
     *
     * @param ratio The aspect ratio value.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner setAspectRatio(String ratio) {
        return handleArguments("-aspect", ratio);
    }

    /**
     * Sets the framerate for the FFmpeg command.
     *
     * @param framerate The framerate value.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner setFramerate(String framerate) {
        return handleArguments("-r", framerate);
    }

    /**
     * Adds metadata for the FFmpeg command.
     *
     * @param key   The metadata key.
     * @param value The metadata value.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner addMetadata(String key, String value) {
        return handleArguments("-metadata", key + "=" + value);
    }

    /**
     * Sets the preset for the FFmpeg command.
     *
     * @param preset The preset value.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner setPreset(String preset) {
        return handleArguments("-preset", preset);
    }

    /**
     * Adds a filter to the FFmpeg command.
     *
     * @param filter The filter to add.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner addFilter(String filter) {
        return handleArguments("-filter", filter);
    }

    /**
     * Adds a complex filter to the FFmpeg command.
     *
     * @param filter The complex filter to add.
     * @return The FFmpegRunner instance.
     */
    public FFmpegRunner addComplexFilter(String filter) {
        return handleArguments("-lavfi", filter);
    }

    /**
     * Private method to handle adding arguments to the FFmpeg command.
     *
     * @param arguments The arguments to add.
     * @return The FFmpegRunner instance.
     */
    private FFmpegRunner handleArguments(String... arguments) {
        for (String arg : arguments) {
            commandRunner.addArgument(arg);
        }
        return this;
    }

    /**
     * Runs the FFmpeg command.
     *
     * @param logOutput Flag to determine whether to log the output of the command.
     * @return true if the command is executed successfully, false otherwise.
     */
    public boolean run(boolean logOutput) {
        return commandRunner.run(logOutput);
    }

}

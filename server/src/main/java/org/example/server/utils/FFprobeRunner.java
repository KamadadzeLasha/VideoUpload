package org.example.server.utils;

/**
 * Utility class to run FFprobe commands.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
public class FFprobeRunner {
    private final CommandRunner commandRunner;

    /**
     * Constructor to initialize the FFprobeRunner with a given CommandRunner.
     *
     * @param commandRunner The CommandRunner instance to use for executing commands.
     */
    public FFprobeRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
        commandRunner.addExecutable("/binaries/ffprobe");
    }

    /**
     * Analyzes the media file using FFprobe.
     *
     * @param filePath The path to the media file.
     * @return The FFprobeRunner instance.
     */
    public FFprobeRunner analyze(String filePath) {
        return handleArguments("-i", filePath);
    }

    /**
     * Shows format information of the media file using FFprobe.
     *
     * @param filePath The path to the media file.
     * @return The FFprobeRunner instance.
     */
    public FFprobeRunner showFormat(String filePath) {
        return handleArguments("-show_format", filePath);
    }

    /**
     * Shows stream information of the media file using FFprobe.
     *
     * @param filePath The path to the media file.
     * @return The FFprobeRunner instance.
     */
    public FFprobeRunner showStreams(String filePath) {
        return handleArguments("-show_streams", filePath);
    }

    /**
     * Shows program information of the media file using FFprobe.
     *
     * @param filePath The path to the media file.
     * @return The FFprobeRunner instance.
     */
    public FFprobeRunner showPrograms(String filePath) {
        return handleArguments("-show_programs", filePath);
    }

    /**
     * Shows data information of the media file using FFprobe.
     *
     * @param filePath The path to the media file.
     * @return The FFprobeRunner instance.
     */
    public FFprobeRunner showData(String filePath) {
        return handleArguments("-show_data", filePath);
    }

    // Private method to handle adding arguments to the command
    private FFprobeRunner handleArguments(String... arguments) {
        for (String arg : arguments) {
            commandRunner.addArgument(arg);
        }
        return this;
    }
}

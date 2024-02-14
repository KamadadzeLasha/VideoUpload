package org.example.server.utils;

import java.util.Objects;

public class FFprobeRunner {
    private final CommandRunner commandRunner;

    public FFprobeRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
        String pathToFFprobeBinary = Objects.requireNonNull(getClass().getResource("/binaries/ffprobe")).getFile();
        commandRunner.addArgument(pathToFFprobeBinary);
    }

    public FFprobeRunner analyze(String filePath) {
        return handleArguments("-i", filePath);
    }

    public FFprobeRunner showFormat(String filePath) {
        return handleArguments("-show_format", filePath);
    }

    public FFprobeRunner showStreams(String filePath) {
        return handleArguments("-show_streams", filePath);
    }

    public FFprobeRunner showPrograms(String filePath) {
        return handleArguments("-show_programs", filePath);
    }

    public FFprobeRunner showData(String filePath) {
        return handleArguments("-show_data", filePath);
    }

    public FFprobeRunner showError(String filePath) {
        return handleArguments("-show_error", filePath);
    }

    public FFprobeRunner showVersion() {
        return handleArguments("-version");
    }

    public FFprobeRunner showLicense() {
        return handleArguments("-license");
    }

    private FFprobeRunner handleArguments(String... arguments) {
        for (String arg : arguments) {
            commandRunner.addArgument(arg);
        }
        return this;
    }
}

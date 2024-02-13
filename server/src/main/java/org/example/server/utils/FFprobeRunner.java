package org.example.server.utils;

public class FFprobeRunner {
    private final String ffprobePath = getClass().getResource("/binaries/ffprobe").getFile();
    private final CommandRunner commandRunner;

    public FFprobeRunner(CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
    }

}

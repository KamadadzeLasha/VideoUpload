package org.example.server.utils;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Utility class to run external commands.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
@Getter
public class CommandRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRunner.class);
    private final List<String> command;

    /**
     * Constructor to initialize the command list.
     */
    public CommandRunner() {
        this.command = new ArrayList<>();
    }

    /**
     * Adds an argument to the command.
     *
     * @param argument The argument to add.
     */
    public void addArgument(String argument) {
        this.command.add(argument);
    }

    /**
     * Adds an executable to the command.
     *
     * @param executable The path to the executable.
     */
    public void addExecutable(String executable) {
        String absolutePathToExecutable = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource(executable)).getFile()).getAbsolutePath();
        this.command.add(absolutePathToExecutable);
    }

    /**
     * Runs the command.
     *
     * @param printOutput Flag to determine whether to print the output of the command.
     * @return true if the command is executed successfully, false otherwise.
     */
    public boolean run(boolean printOutput) {
        try {
            // Execute the command
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            if (printOutput) {
                LOGGER.info("Executing command: {}", String.join(" ", command));

                // Read the output of the command and log it
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        LOGGER.info(line);
                    }
                }
            }
            // Wait for the command to finish
            int exitCode = process.waitFor();
            LOGGER.info("Command exited with code {}", exitCode);

            // Return true if the command exited successfully
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Command has failed to execute: {}", e.getMessage());
            return false;
        }
    }
}

package org.example.server.utils;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Getter
public class CommandRunner {
    private final List<String> command;

    public CommandRunner() {
        this.command = new ArrayList<>();
    }

    public void addArgument(String argument) {
        this.command.add(argument);
    }

    public boolean run(boolean printOutput) {
        try {
            // Execute the command
            Process process = new ProcessBuilder(command).start();

            if (printOutput) {
                // Read the output of the command and print it
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }

            // Wait for the command to finish
            int exitCode = process.waitFor();
            System.out.println("Command exited with code " + exitCode);

            // Return true if the command exited successfully
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            System.out.println("Command has Failed to execute: " + e);
            return false;
        }

    }
}
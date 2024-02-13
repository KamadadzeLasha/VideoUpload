package org.example.server.service;

import org.example.server.model.ConvertedFileInfo;
import org.example.server.model.VideoConversionRequest;
import org.example.server.utils.FFmpegRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {
    private final Path rootLocation = Paths.get("upload-dir");
    private final Path tempLocation = Paths.get("temp-location");


//    @Autowired
//    public FileConversionServiceImpl(StorageProperties properties) {
//        String rootLocation = properties.getRootLocation();
//        String tempLocation = properties.getTempLocation();
//        if (rootLocation.isEmpty() || tempLocation.isEmpty()) {
//            throw new RuntimeException("Location cannot be empty");
//        }
//        this.rootLocation = Paths.get(rootLocation);
//        this.tempLocation = Paths.get(tempLocation);
//        this.rootLocation = Paths
//    }

    @Override
    public void initialize() {
        try {
            System.out.println(rootLocation.toFile().getAbsolutePath());
            Files.createDirectories(rootLocation);
            Files.createDirectories(tempLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
        FileSystemUtils.deleteRecursively(tempLocation.toFile());
    }

    @Override
    public ConvertedFileInfo convertFile(VideoConversionRequest conversionRequest) {
        try {
            String tempFilePath = saveTemporarilyAndGetFilePath(conversionRequest.getMultipartFile());
            String convertedFileName = "Random-" + Objects.requireNonNull(conversionRequest.getMultipartFile().getOriginalFilename());
            Process process = getProcess(convertedFileName, tempFilePath);

            // Handle process output (optional)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("FFMpeg command executed successfully");
            } else {
                throw new RuntimeException("Error executing FFMpeg command, exit code: " + exitCode);
            }
            Files.deleteIfExists(Paths.get(tempFilePath));
            String fileUrl = rootLocation.resolve(convertedFileName).toUri().toString();
            FFmpegRunner runner = new FFmpegRunner();
            // Return information about the transcoded file
            return new ConvertedFileInfo(convertedFileName, fileUrl);
        } catch (IOException | InterruptedException e) {
            return new ConvertedFileInfo("mokda", "mkdaria");
        }
    }

    private Process getProcess(String inputFile, String tempFilePath) throws IOException {
        FFmpegRunner runner = new FFmpegRunner();
        // Execute command
        ProcessBuilder processBuilder = new ProcessBuilder();

        return processBuilder.start();
    }

    private String saveTemporarilyAndGetFilePath(MultipartFile video) throws IOException {
        String tempFilePath = tempLocation.resolve(Objects.requireNonNull(video.getOriginalFilename())).toString();
        Files.copy(video.getInputStream(), Paths.get(tempFilePath));
        return tempFilePath;
    }
}

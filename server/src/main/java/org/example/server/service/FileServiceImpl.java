package org.example.server.service;

import org.example.server.model.ConvertedFileInfo;
import org.example.server.model.VideoConversionRequest;
import org.example.server.utils.FFmpegRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

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

            Files.deleteIfExists(Paths.get(tempFilePath));
            String fileUrl = rootLocation.resolve(convertedFileName).toUri().toString();

            // Return information about the transcoded file
            return new ConvertedFileInfo(convertedFileName, fileUrl);
        } catch (IOException e) {
            return new ConvertedFileInfo("mokda", "mkdaria");
        }
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    public Resource load(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
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

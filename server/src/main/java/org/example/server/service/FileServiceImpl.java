package org.example.server.service;

import org.apache.commons.io.FilenameUtils;
import org.example.server.Properties.StorageProperties;
import org.example.server.controller.FileConverterController;
import org.example.server.model.FileInfo;
import org.example.server.model.VideoConversionRequest;
import org.example.server.model.VideoFile;
import org.example.server.utils.FFmpegRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Implementation of the FileService interface for handling file-related operations.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
@Service
public class FileServiceImpl implements FileService {
    private final Path rootPath;
    private final Path tempPath;

    /**
     * Constructs a FileServiceImpl instance.
     * Initializes the rootPath and tempPath for file storage.
     */
    public FileServiceImpl() {
        this.rootPath = StorageProperties.ROOTLOCATION;
        this.tempPath = StorageProperties.TEMPLOCATION;
    }

    /**
     * Initializes the file storage by creating necessary directories.
     */
    @Override
    public void initialize() {
        try {
            Files.createDirectories(rootPath);
            Files.createDirectories(tempPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage ", e);
        }
    }

    /**
     * Deletes all stored files and reinitialized storage.
     *
     * @throws IOException If an I/O error occurs during file deletion or initialization.
     */
    @Override
    public void deleteAll() throws IOException {
        FileSystemUtils.deleteRecursively(rootPath);
        FileSystemUtils.deleteRecursively(tempPath);
        this.initialize();
    }

    /**
     * Loads all stored files.
     *
     * @return A Stream of paths to the stored files.
     */
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootPath, 1).filter(path -> !path.equals(this.rootPath))
                    .map(this.rootPath::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    /**
     * Loads the file with the specified filename.
     *
     * @param filename The name of the file to load.
     * @return A Resource representing the loaded file.
     */
    @Override
    public Resource load(String filename) {
        try {
            Path file = rootPath.resolve(filename);
            final Resource resource = new UrlResource(
                    Objects.requireNonNull(file.toUri())
            );

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    /**
     * Converts the multimedia file based on the provided conversion request.
     *
     * @param conversionRequest The request containing the details for the file conversion.
     * @return Information about the converted file, including its name and URL.
     * @throws IOException If an I/O error occurs during the conversion process.
     */
    @Override
    public FileInfo convertFile(VideoConversionRequest conversionRequest) throws IOException {
        FFmpegRunner ffmpeg = new FFmpegRunner();
        VideoFile video = conversionRequest.getVideoFile();
        String convertedFileName = "TrendGeorgia" + FilenameUtils.getBaseName(video.getName()) + conversionRequest.getVideoExtensionType();
        Path convertedFilePath = rootPath.resolve(convertedFileName);
        ffmpeg.addInput(video.getInputFilePath())
                .addOutput(convertedFilePath.toAbsolutePath());
        if (conversionRequest.getFrameRate() > 0) {
            ffmpeg.setFramerate(String.valueOf(conversionRequest.getFrameRate()));
        }
        if (conversionRequest.getWidth() > 0 && conversionRequest.getHeight() > 0) {
            ffmpeg.addComplexFilter(String.format("[0:v]scale=%d:%d,boxblur=luma_radius=min(h\\,w)/20:luma_power=1:chroma_radius=min(cw\\,ch)/20:chroma_power=1[bg];[bg][0:v]overlay=(W-w)/2:(H-h)/2,setsar=1", conversionRequest.getWidth(), conversionRequest.getHeight()));
        }
        boolean programExecuted = ffmpeg.run(true); // Get the result of running ffmpeg command
        Files.deleteIfExists(video.getInputFilePath());
        if (programExecuted) {
            URI url = MvcUriComponentsBuilder.fromMethodName(FileConverterController.class, "getFile", convertedFilePath.getFileName().toString()).build().toUri();
            return new FileInfo(convertedFileName, url);
        } else {
            return null;
        }
    }

    /**
     * Saves the uploaded file to the root directory.
     *
     * @param file The multipart file to save.
     */
    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(
                    file.getInputStream(),
                    this.rootPath.resolve(Objects.requireNonNull(file.getOriginalFilename()))
            );
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }
}

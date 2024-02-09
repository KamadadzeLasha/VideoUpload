package org.example.server.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.example.server.Properties.StorageProperties;
import org.example.server.model.ConvertedFileInfo;
import org.example.server.model.VideoConversionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

@Service
public class FileConversionServiceImpl implements FileConversionService {
  private final Path rootLocation;

  @Autowired
  public FileConversionServiceImpl(StorageProperties properties) {
    String location = properties.getLocation();
    if (location.isEmpty()) {
      throw new RuntimeException("Location cannot be empty");
    }
    this.rootLocation = Paths.get(location);
  }

  @Override
  public void initialize() {
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize storage", e);
    }
  }
  
  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

  @Override
  public ConvertedFileInfo convertFile(
    VideoConversionRequest conversionRequest
  ) {
    
    return null;
  }
  

}

package org.example.server.controller;

import org.example.server.model.ConvertedFileInfo;
import org.example.server.model.VideoConversionRequest;
import org.example.server.service.FileConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class FileConverterController {

  private final FileConversionService fileConversionService;

  @Autowired
  public FileConverterController(FileConversionService fileConversionService) {
    this.fileConversionService = fileConversionService;
  }

  @PostMapping("/")
  public ResponseEntity<ConvertedFileInfo> convertVideo(
    @ModelAttribute VideoConversionRequest conversionRequest
  ) {
    ConvertedFileInfo fileInfo = fileConversionService.convertFile(
      conversionRequest
    );

    return ResponseEntity
      .status(HttpStatus.OK)
      .header("Content-Type", "video/mp4")
      .header(
        "Content-Disposition",
        "attachment; filename=" + fileInfo.getFileName()
      )
      .body(fileInfo);
  }
}

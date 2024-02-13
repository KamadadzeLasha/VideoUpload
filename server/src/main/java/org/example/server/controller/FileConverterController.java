package org.example.server.controller;

import org.example.server.model.ConvertedFileInfo;
import org.example.server.model.VideoConversionRequest;
import org.example.server.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class FileConverterController {

    private final FileService fileService;

    @Autowired
    public FileConverterController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(path = {""})
    public ResponseEntity<ConvertedFileInfo> convertVideo(
            @ModelAttribute VideoConversionRequest conversionRequest
    ) {
        ConvertedFileInfo fileInfo = fileService.convertFile(
                conversionRequest
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileInfo);
    }
}

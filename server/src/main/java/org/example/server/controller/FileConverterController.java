package org.example.server.controller;

import org.example.server.message.ResponseMessage;
import org.example.server.model.FileInfo;
import org.example.server.model.VideoConversionRequest;
import org.example.server.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for handling file conversion and management operations.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class FileConverterController {

    private final FileService fileService;

    /**
     * Constructor injection for FileService dependency.
     *
     * @param fileService The FileService instance.
     */
    @Autowired
    public FileConverterController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Endpoint for converting a video file and loading it.
     *
     * @param conversionRequest The video conversion request containing details for the conversion.
     * @return ResponseEntity containing information about the converted file.
     * @throws IOException If an I/O error occurs during file conversion.
     */
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FileInfo> convertVideoAndLoad(
            @RequestPart(name = "multipartFile")
            @ModelAttribute VideoConversionRequest conversionRequest
    ) throws IOException {
        FileInfo fileInfo = fileService.convertFile(conversionRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getName() + "\"")
                .body(fileInfo);
    }

    /**
     * Endpoint for uploading a file.
     *
     * @param file The multipart file to be uploaded.
     * @return ResponseEntity containing a response message indicating the status of the upload.
     */
    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("multipartFile") MultipartFile file) {
        String message;
        try {
            fileService.save(file);
            message = "File " + file.getOriginalFilename() + " uploaded successfully.";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    /**
     * Endpoint for deleting all files.
     *
     * @return ResponseEntity containing a response message indicating the status of the delete operation.
     */
    @DeleteMapping()
    public ResponseEntity<ResponseMessage> deleteAll() {
        try {
            fileService.deleteAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("All files deleted!"));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Could not Delete Files from store: " + e));
        }
    }

    /**
     * Endpoint for downloading a file.
     *
     * @param filename The name of the file to download.
     * @return ResponseEntity containing the file as a resource for download.
     */
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    /**
     * Endpoint for getting a list of all files.
     *
     * @return ResponseEntity containing a list of file information.
     */
    @GetMapping()
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = fileService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            URI url = MvcUriComponentsBuilder.fromMethodName(FileConverterController.class, "getFile", path.getFileName().toString()).build().toUri();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
}

package org.example.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class VideoConversionRequest {

    // private URI url;  ## This field will be available if File itself will be uploaded to the server and the link of that File willll be fetched

    private MultipartFile multipartFile;
    private TargetPlatform targetPlatform;
//    private VideoExtension videoExtension;

}

# File Conversion and Management API Documentation

#### This API provides endpoints for converting video files, uploading files, deleting files, downloading files, and getting a list of all files.
## Endpoints
1. Convert Video and Load

   - **Method**: POST
   - **Endpoint**: /
   - **Consumes**: multipart/form-data
   - **Request Parameters**:
     - **multipartFile**: The video file to be converted.
   - **Request** Body: VideoConversionRequest object containing the following fields:
     - **videoFile**: (Required) The video file to be converted.
     - **validExtensionType**: The valid extension type for the converted video file (optional).
     - **frameRate**: The frame rate of the converted video (optional).
     - **width**: The width of the converted video (optional).
     - **height**: The height of the converted video (optional).
   - **Response**: FileInfo object containing information about the converted file.
   - **Status Codes**:
     - 200 OK: Successful conversion and file loading.
     - 417 Expectation Failed: If an error occurs during conversion or file loading.

2. Upload File

   - Method: POST
   - Endpoint: /save
   - Request Parameters:
   - multipartFile: The file to be uploaded.
   - Response: ResponseMessage object containing a message indicating the status of the upload.
   - Status Codes:
   - 200 OK: File uploaded successfully.
   - 417 Expectation Failed: If an error occurs during the upload process.

3. Delete All Files

   - Method: DELETE
   - Endpoint: /
   - Response: ResponseMessage object containing a message indicating the status of the delete operation.
   - Status Codes:
     - 200 OK: All files deleted successfully.
     - 417 Expectation Failed: If an error occurs during the delete process.

4. Download File

   - Method: GET
   - Endpoint: /{filename:.+}
   - Path Parameter: filename: The name of the file to download.
   - Response: The file as a resource for download.
   - Status Codes:
     - 200 OK: File download successful.
     - 404 Not Found: If the specified file does not exist.

5. Get List of Files

   - Method: GET
   - Endpoint: /
   - Response: List of FileInfo objects containing information about all files.
   - Status Codes:
     - 200 OK: List of files retrieved successfully.
     - 417 Expectation Failed: If an error occurs during the retrieval process.


### Request and Response Formats

VideoConversionRequest Object
```json
{
  "videoFile": "multipart file",
  "validExtensionType": "string",
  "frameRate": "number",
  "width": "number",
  "height": "number"
}
```

FileInfo Object
```json
{
  "name": "string",
  "fileUrl": "URI"
}
```

ResponseMessage Object
```json
{
  "message": "string"
}
```


#### Example 1: Convert Video and Load

_Request:_

```http request
POST / HTTP/1.1
Host: example.com
Content-Type: multipart/form-data

multipartFile=<video file>&validExtensionType=mp4&frameRate=30&width=640&height=480
```

_Response (200 OK):_

```json

{
"name": "converted_video.mp4",
"fileUrl": "http://example.com/converted_video.mp4"
}
```

Example 2: Upload File

_Request:_

```http
POST /save HTTP/1.1
Host: example.com
Content-Type: multipart/form-data
multipartFile=<file>
```
_Response (200 OK):_

```json
{
"message": "File file.txt uploaded successfully."
}
```


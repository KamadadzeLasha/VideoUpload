package org.example.server.exception;

import org.apache.catalina.connector.ClientAbortException;
import org.example.server.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * Global exception handler for file upload related exceptions.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */

@ControllerAdvice
public class FileUploadExceptionAdvice {

    /**
     * Handles MaxUploadSizeExceededException and returns an appropriate response.
     *
     * @param exc The MaxUploadSizeExceededException instance.
     * @return ResponseEntity containing a response message with the error message.
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(exc.getMessage()));
    }

    /**
     * Handles RuntimeException and returns an appropriate response.
     *
     * @param e The RuntimeException instance.
     * @return ResponseEntity containing a response message with the error message.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseMessage> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
    }

    /**
     * Handles ClientAbortException and returns an appropriate response.
     *
     * @param ignored The ClientAbortException instance.
     * @return ResponseEntity containing a response message indicating request abortion.
     */
    @ExceptionHandler(ClientAbortException.class)
    public ResponseEntity<ResponseMessage> handleClientAbortException(ClientAbortException ignored) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Request aborted from the user's side." ));
    }
}

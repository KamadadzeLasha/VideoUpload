package org.example.server.message;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a response message.
 *
 * @author Lasha Kamadadze
 * @version 0.1
 * Date: 14.02.2024
 */
@Data
@AllArgsConstructor
public class ResponseMessage {
    /**
     * The message content.
     */
    private String message;
}

package com.example.martwallet.dto.responsedto;

// ErrorResponse.java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private int status;
//    public ErrorResponse(LocalDateTime timestamp, String message, String details, int status) {
//        this.timestamp = timestamp;
//        this.message = message;
//        this.details = details;
//        this.status = status;
//    }
}


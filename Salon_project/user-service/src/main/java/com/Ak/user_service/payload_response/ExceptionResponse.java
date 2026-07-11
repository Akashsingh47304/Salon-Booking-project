package com.Ak.user_service.payload_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExceptionResponse {
    private String message;
    private String error;
    private LocalDateTime timestamp;

}

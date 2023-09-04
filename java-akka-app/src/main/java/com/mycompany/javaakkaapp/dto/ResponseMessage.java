package com.mycompany.javaakkaapp.dto;

import lombok.Data;

@Data
public class ResponseMessage {
    private final String message;

    public ResponseMessage(String message) {
        this.message = message;
    }
}

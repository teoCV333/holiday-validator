package com.taller1.holidayvalidator.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponseModel {
    public static <T> ResponseEntity<T>     createResponse(HttpStatus status, T data) {
        return new ResponseEntity<>(data, status);
    }
}
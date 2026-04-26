package com.orbis.kutuphane;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("zaman", LocalDateTime.now());
        body.put("mesaj", ex.getMessage());
        body.put("hata", "Giriş Hatası");

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}

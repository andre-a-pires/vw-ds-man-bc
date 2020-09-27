package com.volkswagen.digitalservices.manbackendchallenge.restfulapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class Status {

    @GetMapping("/test")
    public ResponseEntity handleStatus() {
        return ResponseEntity.ok("status ok");
    }
}

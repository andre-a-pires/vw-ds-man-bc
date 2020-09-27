package com.volkswagen.digitalservices.manbackendchallenge.restfulapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class Status {
    public static final String OK_BODY = "status ok";

    @GetMapping(Paths.STATUS)
    public ResponseEntity getValue() {
        return ResponseEntity.ok(OK_BODY);
    }
}

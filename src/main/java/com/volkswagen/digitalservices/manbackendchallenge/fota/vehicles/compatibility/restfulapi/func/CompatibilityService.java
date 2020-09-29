package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.conf.Paths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CompatibilityService {
    public static final String OK_BODY = "status ok!";

    @GetMapping(Paths.VEHICLES + Paths.VEHICLES_STATUS)
    public ResponseEntity getValue() {
        return ResponseEntity.ok(OK_BODY);
    }

    @GetMapping(Paths.VEHICLES + "/{vin}" + Paths.VEHICLES_INSTALLABLE)
    public ResponseEntity getInstallable(@PathVariable String vin) {
        return ResponseEntity.ok("Vehicles installable request with vin=" + vin);
    }

    @GetMapping(Paths.VEHICLES + "/{vin}" + Paths.VEHICLES_INCOMPATIBLE)
    public ResponseEntity getIncompatible(@PathVariable String vin) {
        return ResponseEntity.ok("Vehicles incompatible request with vin=" + vin);
    }
}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf.DaemonConfiguration;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func.DaemonService;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.persistence.Feature;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.persistence.FeatureService;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.conf.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Paths.VEHICLES)
public final class CompatibilityService {
    static final Logger LOGGER = LoggerFactory.getLogger(CompatibilityService.class);

    public static final String OK_BODY = "status ok!";

    @Autowired
    FeatureService featureService;

    @Autowired
    private DaemonConfiguration config;

    @GetMapping(Paths.VEHICLES_STATUS)
    public ResponseEntity getValue() {
        return ResponseEntity.ok(OK_BODY);
    }

    @GetMapping("/{vin}" + Paths.VEHICLES_INSTALLABLE)
    public ResponseEntity getInstallable(@PathVariable String vin) {
        return ResponseEntity.ok("Vehicles installable request with vin=" + vin);
    }

    @GetMapping("/{vin}" + Paths.VEHICLES_INCOMPATIBLE)
    public ResponseEntity getIncompatible(@PathVariable String vin) {
        return ResponseEntity.ok("Vehicles incompatible request with vin=" + vin);
    }

    // test purposes
//    @GetMapping("/test")
//    public ResponseEntity testPersistence() {
//        featureService.add(new Feature( "Our very first feature!!!"));
//        featureService.add(new Feature( "Our very second feature!!!"));
//
//        Feature feature = featureService.getFeatureById(1l)
//                .orElseThrow(() -> new RuntimeException("Feature 1l not found!!!"));
//
//        LOGGER.info("Feature found!!!!!!!!!!!!!!!!!!!!!");
//
//        return ResponseEntity.ok("");
//    }
}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf.DaemonConfiguration;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.vehicle.VehicleCompatibilityService;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.conf.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.conf.Paths.*;

@RestController
@RequestMapping(Paths.VEHICLES)
public final class CompatibilityService {
    static final Logger LOGGER = LoggerFactory.getLogger(CompatibilityService.class);

    public static final String OK_BODY = "status ok!";

    @Autowired
    VehicleCompatibilityService service;

    @Autowired
    private DaemonConfiguration config;

    @GetMapping(VEHICLES_STATUS)
    public ResponseEntity getValue() {
        LOGGER.info("New request on " + Paths.VEHICLES + VEHICLES_STATUS);
        return ResponseEntity.ok(OK_BODY);
    }

    @GetMapping("/{vin}" + VEHICLES_INSTALLABLE)
    public ResponseEntity getInstallable(@PathVariable String vin) {
        LOGGER.info("New request on " + Paths.VEHICLES + "/" + vin + VEHICLES_INSTALLABLE);

        if (!service.vehicleExists(vin)) {
            LOGGER.info("Requested vehicle with vin=" + vin + " does not exist");
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Vehicles installable request with vin=" + vin);
    }

    @GetMapping("/{vin}" + VEHICLES_INCOMPATIBLE)
    public ResponseEntity getIncompatible(@PathVariable String vin) {
        LOGGER.info("New request on " + Paths.VEHICLES + "/" + vin + VEHICLES_INCOMPATIBLE);

        if (!service.vehicleExists(vin)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Vehicles incompatible request with vin=" + vin);
    }

}

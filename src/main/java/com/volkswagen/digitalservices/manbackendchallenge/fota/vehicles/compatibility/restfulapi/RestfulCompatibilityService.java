package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.compatibility.data.VehicleNotFoundException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.compatibility.func.CompatibilityService;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.func.VehicleService;
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
public final class RestfulCompatibilityService {
    static final Logger LOGGER = LoggerFactory.getLogger(RestfulCompatibilityService.class);

    public static final String OK_BODY = "status ok!";

    @Autowired
    VehicleService vehicleService;

    @Autowired
    CompatibilityService compatibilityService;

    @GetMapping(VEHICLES_STATUS)
    public ResponseEntity getValue() {
        LOGGER.info("New request on " + Paths.VEHICLES + VEHICLES_STATUS);
        return ResponseEntity.ok(OK_BODY);
    }

    // TODO: create DTOs for rest response bodies

    @GetMapping("/{vin}" + VEHICLES_INSTALLABLE)
    public ResponseEntity getInstallable(@PathVariable String vin) {
        LOGGER.info("New request on " + Paths.VEHICLES + "/" + vin + VEHICLES_INSTALLABLE);

        try {
            return ResponseEntity.ok(compatibilityService.getInstallableFeatures(vin));
        } catch (VehicleNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{vin}" + VEHICLES_INCOMPATIBLE)
    public ResponseEntity getIncompatible(@PathVariable String vin) {
        LOGGER.info("New request on " + Paths.VEHICLES + "/" + vin + VEHICLES_INCOMPATIBLE);

        if (!vehicleService.vehicleExists(vin)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Vehicles incompatible request with vin=" + vin);
    }

}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleCompatibilityService {
    @Autowired
    VehicleRepository repository;

    public boolean vehicleExists(String vin) {
        return !repository.findByVin(vin).isEmpty();
    }

}

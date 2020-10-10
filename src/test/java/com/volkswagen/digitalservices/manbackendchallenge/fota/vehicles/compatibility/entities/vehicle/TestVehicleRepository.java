package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.vehicle;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.SoftwareCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TestVehicleRepository {
    @Autowired
    VehicleRepository repo;

    @Test
    public void testRepo() throws InvalidCodeStructureException {
        final String vin = "vin-code";
        final Code softCode = new SoftwareCode("my-code");
        Vehicle vehicle = new Vehicle(vin, softCode);

        final List<Vehicle> emptyVehicleObj = repo.findByVin(vin);
        assertTrue(emptyVehicleObj.isEmpty());

        final Vehicle persistedVehicle = repo.save(vehicle);
        assertTrue(persistedVehicle.getVin().equals(vehicle.getVin()));

        final List<Vehicle> fetchedVehicles = repo.findByVin(vin);
        assert(fetchedVehicles.size() == 1);
        final Vehicle fetchedVehicle = fetchedVehicles.get(0);
        assertTrue(fetchedVehicle.getVin().equals(persistedVehicle.getVin()));
    }
}

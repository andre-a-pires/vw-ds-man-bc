package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.api;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.SoftwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.data.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(persistedVehicle.getVin(), vehicle.getVin());

        final List<Vehicle> fetchedVehicles = repo.findByVin(vin);
        assert(fetchedVehicles.size() == 1);
        final Vehicle fetchedVehicle = fetchedVehicles.get(0);
        assertEquals(fetchedVehicle.getVin(), persistedVehicle.getVin());
    }
}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.vehicle;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf.DaemonConfiguration;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.VinCodePair;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func.DaemonService;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.CodeRepository;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.SoftwareCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestVehicleService {
    @Autowired
    VehicleService service;

    @Autowired
    DaemonService daemon;

    @Autowired
    DaemonConfiguration config;

    @MockBean
    VehicleRepository vehicleRepo;

    @Test
    public void testPersistIfNew() throws InvalidCodeStructureException {
        Code softCode = new SoftwareCode("my-code");
        Vehicle vehicleToPersist = new Vehicle("my-vin", softCode);
        Vehicle vehiclePersisted = new Vehicle(vehicleToPersist.getVin(), vehicleToPersist.getSoftwareCodes());

        when(vehicleRepo.save(vehicleToPersist)).thenReturn(vehiclePersisted);

        service.persistIfNew(new VinCodePair(vehicleToPersist.getVin(), softCode));

        verify(vehicleRepo, times(1)).save(any(Vehicle.class));
    }
}

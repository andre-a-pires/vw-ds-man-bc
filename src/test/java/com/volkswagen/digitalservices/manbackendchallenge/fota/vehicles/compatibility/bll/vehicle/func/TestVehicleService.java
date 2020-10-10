package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.SoftwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.api.VehicleRepository;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.data.Vehicle;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.VinCodePair;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestVehicleService {
    @Autowired
    VehicleService underTest;

    @MockBean
    VehicleRepository vehicleRepo;

    final String vin = "my-vin";
    final Code softCode = new SoftwareCode("my-code");
    Vehicle vehicleToPersist = new Vehicle(vin, softCode);

    public TestVehicleService() throws InvalidCodeStructureException {
    }

    @Test
    public void testPersistIfNewWhenItIsNew() throws InvalidCodeStructureException {
        underTest.persistIfNew(new VinCodePair(vehicleToPersist.getVin(), softCode));

        verify(vehicleRepo, times(1)).findByVin(vin);
        verify(vehicleRepo, times(1)).save(any(Vehicle.class));
    }

    @Test
    public void testPersistIfNewWhenItIsNotNew() throws InvalidCodeStructureException {
        when(vehicleRepo.findByVin(eq(vin))).thenReturn(Lists.list(vehicleToPersist));

        underTest.persistIfNew(new VinCodePair(vehicleToPersist.getVin(), softCode));

        verify(vehicleRepo, times(1)).findByVin(vin);
        verify(vehicleRepo, times(0)).save(any(Vehicle.class));
    }
}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.vehicle;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.VinCodePair;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private CodeRepository codeRepo;

    public Vehicle persistIfNew(VinCodePair pair) throws InvalidCodeStructureException {
        List<Vehicle> vehicles = vehicleRepo.findByVin(pair.getVin())
                .stream().filter(v -> (pair.isSoftwareCode() && v.getSoftwareCodes().contains(pair.getCode()))
                        || (pair.isHardwareCode() && v.getHardwareCodes().contains(pair.getCode())))
                .collect(Collectors.toList());

        if (vehicles.isEmpty()) {
            Vehicle vehicle = new Vehicle(pair.getVin(), pair.getCode());
            return vehicleRepo.save(vehicle);
        } else {
            assert vehicles.size() == 1;
            return vehicles.get(0);
        }
    }

    public Vehicle getVehicleWithVin(String vin) {
        List<Vehicle> results = vehicleRepo.findByVin(vin);
        assert results.size() <= 1;
        return results.get(0);
    }

}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.api.CodeRepository;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.api.VehicleRepository;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.data.Vehicle;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.VinCodePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleService {
    static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private CodeRepository codeRepo;

    public boolean vehicleExists(String vin) {
        return !vehicleRepo.findByVin(vin).isEmpty();
    }

    public Vehicle persistIfNew(VinCodePair pair) throws InvalidCodeStructureException {
        List<Vehicle> vehiclesWithVin = vehicleRepo.findByVin(pair.getVin());

        if (vehiclesWithVin.isEmpty()) {
            Vehicle vehicle = new Vehicle(pair.getVin(), pair.getCode());
            return vehicleRepo.save(vehicle);

        } else {
            assert vehiclesWithVin.size() == 1;

            List<Vehicle> vehiclesWithVinAndCode = vehiclesWithVin.stream()
                    .filter(v -> (pair.isSoftwareCode() && v.getSoftwareCodes().contains(pair.getCode()))
                        || (pair.isHardwareCode() && v.getHardwareCodes().contains(pair.getCode())))
                .collect(Collectors.toList());

            if (vehiclesWithVinAndCode.size() > 0) {
                assert vehiclesWithVinAndCode.size() == 1;
                return vehiclesWithVinAndCode.get(0);

            } else {
                Vehicle vehicle = vehiclesWithVin.get(0);

                vehicle.addCode(pair.getCode());

                return vehicleRepo.save(vehicle);
            }

        }

    }

    public Vehicle getVehicleWithVin(String vin) {
        List<Vehicle> results = vehicleRepo.findByVin(vin);
        assert results.size() <= 1;
        return results.get(0);
    }

}

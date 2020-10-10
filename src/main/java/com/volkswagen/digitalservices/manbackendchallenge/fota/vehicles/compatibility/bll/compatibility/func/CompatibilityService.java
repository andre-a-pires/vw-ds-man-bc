package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.compatibility.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.compatibility.data.VehicleNotFoundException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.feature.data.Feature;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.feature.data.FeatureCatalogue;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.data.Vehicle;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.func.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: use interfaces for injected dependencies

@Component
public class CompatibilityService {
    static final Logger LOGGER = LoggerFactory.getLogger(CompatibilityService.class);

    @Autowired
    private VehicleService vehicleService;

    public Set<String> getInstallableFeatures(String vin) throws VehicleNotFoundException {
        if (! vehicleService.vehicleExists(vin)) {
            LOGGER.info("Requested vehicle with vin=" + vin + " does not exist");
            throw new VehicleNotFoundException();
        }

        final Vehicle vehicle = vehicleService.getVehicleWithVin(vin);
        final List<Feature> catalogue = FeatureCatalogue.getCatalogue();

        return catalogue.stream()
                .filter(f -> vehicle.getSoftwareCodes().containsAll(f.getMandatorySoftwareCodes()))
                .filter(f -> vehicle.getSoftwareCodes().retainAll(f.getForbiddenSoftwareCodes()) && vehicle.getSoftwareCodes().isEmpty())
                .filter(f -> vehicle.getHardwareCodes().containsAll(f.getMandatoryHardwareCodes()))
                .filter(f -> vehicle.getHardwareCodes().retainAll(f.getForbiddenHardwareCodes()) && vehicle.getHardwareCodes().isEmpty())
                .map(f -> f.getName())
                .collect(Collectors.toSet());
    }

    public Set<String> getIncompatibleFeatures(String vin) throws VehicleNotFoundException {
        if (! vehicleService.vehicleExists(vin)) {
            LOGGER.info("Requested vehicle with vin=" + vin + " does not exist");
            throw new VehicleNotFoundException();
        }

        final Set<String> installableFeatures = getInstallableFeatures(vin);

        return FeatureCatalogue.getCatalogue().stream()
                .map(f -> f.getName())
                .filter(f -> ! installableFeatures.contains(f))
                .collect(Collectors.toSet());
    }

}

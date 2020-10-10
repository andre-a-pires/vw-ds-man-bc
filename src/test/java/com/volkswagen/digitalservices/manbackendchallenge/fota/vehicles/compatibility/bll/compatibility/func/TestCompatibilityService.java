package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.compatibility.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.SoftwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.compatibility.data.VehicleNotFoundException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.feature.data.Feature;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.feature.data.FeatureCatalogue;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.data.Vehicle;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.func.VehicleService;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestCompatibilityService {
    @Autowired
    CompatibilityService underTest;

    @MockBean
    VehicleService vehicleService;

    private final String vin = "my-vin";

    @Test
    public void testGetInstallableFeaturesReturnsA() throws VehicleNotFoundException {
        final List<Feature> catalogue = FeatureCatalogue.getCatalogue();
        assertFalse(catalogue.isEmpty());

        final Feature featureA = catalogue.get(0);

        final Set<Code> featureAMandatoryCodes = new HashSet<>();
        featureAMandatoryCodes.addAll(featureA.getMandatorySoftwareCodes());
        featureAMandatoryCodes.addAll(featureA.getMandatoryHardwareCodes());

        final Vehicle featureAVehicle = new Vehicle(vin, featureAMandatoryCodes);

        when(vehicleService.vehicleExists(vin)).thenReturn(true);
        when(vehicleService.getVehicleWithVin(vin)).thenReturn(featureAVehicle);

        Set<String> installableFeatures = underTest.getInstallableFeatures(vin);
        assertNotNull(installableFeatures);
        assertEquals(installableFeatures.size(), 1);

        final String targetFeature = installableFeatures.iterator().next();
        assertEquals(targetFeature, featureA.getName());
    }

    @Test
    public void testGetInstallableFeaturesReturnsNone() throws VehicleNotFoundException {
        final List<Feature> catalogue = FeatureCatalogue.getCatalogue();
        assertFalse(catalogue.isEmpty());

        final Feature featureA = catalogue.get(0);

        final Set<Code> wrongCombinationCodes = new HashSet<>();
        wrongCombinationCodes.addAll(featureA.getMandatorySoftwareCodes());
        wrongCombinationCodes.addAll(featureA.getForbiddenHardwareCodes());

        final Vehicle noFeaturesVehicle = new Vehicle(vin, wrongCombinationCodes);

        when(vehicleService.vehicleExists(vin)).thenReturn(true);
        when(vehicleService.getVehicleWithVin(vin)).thenReturn(noFeaturesVehicle);

        Set<String> installableFeatures = underTest.getInstallableFeatures(vin);
        assertNotNull(installableFeatures);
        assertTrue(installableFeatures.isEmpty());
    }

    @Test
    public void testGetIncompatibleFeatures() throws InvalidCodeStructureException, VehicleNotFoundException {
        final String vin = "some-vin";
        final Vehicle vehicle = new Vehicle(vin, new SoftwareCode("some-code"));

        when(vehicleService.vehicleExists(vin)).thenReturn(true);
        when(vehicleService.getVehicleWithVin(vin)).thenReturn(vehicle);

        Set<String> incompatibleFeatures = underTest.getIncompatibleFeatures(vin);
        assertNotNull(incompatibleFeatures);
        assertTrue(incompatibleFeatures.containsAll(FeatureCatalogue.getCatalogue().stream().map(Feature::getName).collect(Collectors.toSet())));
    }
}

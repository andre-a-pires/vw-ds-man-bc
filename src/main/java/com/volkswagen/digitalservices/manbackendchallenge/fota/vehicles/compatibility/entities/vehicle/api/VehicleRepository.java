package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.vehicle.api;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.vehicle.data.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    Iterable<Vehicle> findAll();

    List<Vehicle> findByVin(String vin);

}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FeatureRepository extends CrudRepository<Feature, Long> {

    Feature save(Feature feature);

    Optional<Feature> findById(Long id);

}

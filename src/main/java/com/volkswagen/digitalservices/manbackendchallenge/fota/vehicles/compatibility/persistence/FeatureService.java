package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FeatureService {

    @Autowired
    FeatureRepository repository;

    public void add(Feature feature) {
        repository.save(feature);
    }

    public Optional<Feature> getFeatureById(Long id) {
        return repository.findById(id);
    }

}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CodeService {
    @Autowired
    CodeRepository repository;

    public void add(Code code) {
        repository.save(code);
    }

    public Optional<Code> getCodeById(Long id) {
        return repository.findById(id);
    }

}

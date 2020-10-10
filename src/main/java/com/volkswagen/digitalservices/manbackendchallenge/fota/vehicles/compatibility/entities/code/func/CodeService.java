package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.api.CodeRepository;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.data.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CodeService {
    @Autowired
    CodeRepository repository;

    public void persist(Code code) {
        repository.save(code);
    }

    public Optional<Code> getCodeById(Long id) {
        return repository.findById(id);
    }

}

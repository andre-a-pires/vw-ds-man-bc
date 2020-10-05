package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CodeRepository extends CrudRepository<Code, Long> {
    Code save(Code code);

    Optional<Code> findById(Long id);

}

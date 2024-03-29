package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.api;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.Code;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CodeRepository extends CrudRepository<Code, Long> {
    Code save(Code code);

    Optional<Code> findById(Long id);

}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(CodeSpecs.Type.SOFTWARE)
public class SoftwareCode extends Code {
    public SoftwareCode(String code) {
        super(code);
    }

}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(CodeSpecs.Type.HARDWARE)
public class HardwareCode extends Code {
    // ORM usage
    protected HardwareCode() {
        super();
    }

    public HardwareCode(String code) {
        super(code);
    }

}

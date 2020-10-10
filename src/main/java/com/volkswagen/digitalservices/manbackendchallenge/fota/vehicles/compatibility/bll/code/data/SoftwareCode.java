package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(CodeSpecs.Type.SOFTWARE)
public class SoftwareCode extends Code {
    // ORM usage
    protected SoftwareCode() {
        super();
    }

    public SoftwareCode(String code) {
        super(code);
    }

}

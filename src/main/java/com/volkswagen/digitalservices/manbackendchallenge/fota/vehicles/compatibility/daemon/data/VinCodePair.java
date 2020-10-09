package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.HardwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.SoftwareCode;

public class VinCodePair {
    private String vin;
    private Code code;

    public VinCodePair(String vin, Code code) {
        this.vin = vin;
        this.code = code;
    }

    public String getVin() {
        return vin;
    }

    public Code getCode() {
        return code;
    }

    public boolean isSoftwareCode() {
        return this.code instanceof SoftwareCode;
    }

    public boolean isHardwareCode() {
        return this.code instanceof HardwareCode;
    }

}

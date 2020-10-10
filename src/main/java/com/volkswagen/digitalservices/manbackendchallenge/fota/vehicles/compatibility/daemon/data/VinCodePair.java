package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.HardwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.SoftwareCode;

public class VinCodePair {
    private String vin;
    private Code code;

    public VinCodePair(String vin, Code code) {
        assert vin != null;
        assert code != null;

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

    @Override
    public String toString() {
        return "VinCodePair[vin=" + vin + "vin" + ", code=" + code + "]";
    }
}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.feature;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.Code;

import java.util.Set;

public final class Feature {
    private String name;
    private Set<Code> softwareCodesMandatory;
    private Set<Code> softwareCodesUnallowed;
    private Set<Code> hardwareCodesMandatory;
    private Set<Code> hardwareCodesUnallowed;

    protected Feature(String name, Set<Code> softwareCodesMandatory, Set<Code> softwareCodesUnallowed
            , Set<Code> hardwareCodesMandatory, Set<Code> hardwareCodesUnallowed) {
        this.name = name;
        this.softwareCodesMandatory = softwareCodesMandatory;
        this.softwareCodesUnallowed = softwareCodesUnallowed;
        this.hardwareCodesMandatory = hardwareCodesMandatory;
        this.hardwareCodesUnallowed = hardwareCodesUnallowed;
    }

    public String getName() {
        return this.name;
    }

    public Set<Code> getMandatorySoftwareCodes() {
        return this.softwareCodesMandatory;
    }

    public Set<Code> getForbiddenSoftwareCodes() {
        return this.softwareCodesUnallowed;
    }

    public Set<Code> getMandatoryHardwareCodes() {
        return this.hardwareCodesMandatory;
    }

    public Set<Code> getForbiddenHardwareCodes() {
        return this.hardwareCodesUnallowed;
    }

}

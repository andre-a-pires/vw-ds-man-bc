package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.feature.data;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.CodeSpecs;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.HardwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.SoftwareCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FeatureBuilder {
    static final Logger LOGGER = LoggerFactory.getLogger(FeatureBuilder.class);

    private String name;
    private Set<Code> softwareCodesMandatory;
    private Set<Code> softwareCodesUnallowed;
    private Set<Code> hardwareCodesMandatory;
    private Set<Code> hardwareCodesUnallowed;

    public FeatureBuilder() {
        this.softwareCodesMandatory = new HashSet<>();
        this.softwareCodesUnallowed = new HashSet<>();
        this.hardwareCodesMandatory = new HashSet<>();
        this.hardwareCodesUnallowed = new HashSet<>();
    }

    public FeatureBuilder setName(String value) {
        this.name = value;
        return this;
    }

    public FeatureBuilder addMandatorySoftwareCode(String... codes) {
        Arrays.stream(codes).forEach(code -> addCode(new SoftwareCode(code), CodeSpecs.Installation.MANDATORY));
        return this;
    }

    public FeatureBuilder addUnallowedSoftwareCode(String... codes) {
        Arrays.stream(codes).forEach(code -> addCode(new SoftwareCode(code), CodeSpecs.Installation.UNALLOWED));
        return this;
    }

    public FeatureBuilder addMandatoryHardwareCode(String... codes) {
        Arrays.stream(codes).forEach(code -> addCode(new HardwareCode(code), CodeSpecs.Installation.MANDATORY));
        return this;
    }

    public FeatureBuilder addUnallowedHardwareCode(String... codes) {
        Arrays.stream(codes).forEach(code -> addCode(new HardwareCode(code), CodeSpecs.Installation.UNALLOWED));
        return this;
    }

    public Feature build() {
        assert this.name != null;

        return new Feature(
                this.name,
                this.softwareCodesMandatory,
                this.softwareCodesUnallowed,
                this.hardwareCodesMandatory,
                this.hardwareCodesUnallowed);
    }

    private void addCode(Code code, CodeSpecs.Installation installation) {
        // FIXME: refactor needed: pattern not ok, duplicated code
        if (code instanceof SoftwareCode) {
            switch (installation) {
                case MANDATORY:
                    this.softwareCodesMandatory.add(code);
                    break;
                case UNALLOWED:
                    this.softwareCodesUnallowed.add(code);
                    break;
                default:
                    LOGGER.warn("Unsupported installation value: " + installation);
            }
        } else if (code instanceof HardwareCode) {
            switch (installation) {
                case MANDATORY:
                    this.hardwareCodesMandatory.add(code);
                    break;
                case UNALLOWED:
                    this.hardwareCodesUnallowed.add(code);
                    break;
                default:
                    LOGGER.warn("Unsupported installation value: " + installation);
            }
        } else {
            LOGGER.warn("Unsupported code type: " + code.getClass().getSimpleName());
        }

    }

}

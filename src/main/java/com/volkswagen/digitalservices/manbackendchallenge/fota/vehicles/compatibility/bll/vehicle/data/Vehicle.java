package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.data;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.HardwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.SoftwareCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "vehicle", indexes = {@Index(name = "index_vehicle_vin", unique = true, columnList = "vin")})
public class Vehicle {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin", nullable = false, unique = true)
    private String vin;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Code> softwareCodes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Code> hardwareCodes = new HashSet<>();

    protected Vehicle() {}

    public Vehicle(String vin, Code code) throws InvalidCodeStructureException {
        this.vin = vin;
        this.creationDateTime = LocalDateTime.now();
        if (code instanceof SoftwareCode) {
            this.softwareCodes.add(code);
        } else if (code instanceof HardwareCode) {
            this.hardwareCodes.add(code);
        } else {
            throw new InvalidCodeStructureException("Unsupported code type!");
        }
    }

    public Vehicle(String vin, Set<? extends Code> codes) {
        this.vin = vin;
        this.creationDateTime = LocalDateTime.now();
        this.softwareCodes = codes.stream().filter(c -> c instanceof SoftwareCode)
                .map(c -> new SoftwareCode(c.getValue()))
                .collect(Collectors.toSet());
        this.hardwareCodes = codes.stream().filter(c -> c instanceof HardwareCode)
                .map(c -> new HardwareCode(c.getValue()))
                .collect(Collectors.toSet());
    }

    public String getVin() {
        return this.vin;
    }

    public Set<Code> getSoftwareCodes() {
        return softwareCodes;
    }

    public Set<Code> getHardwareCodes() {
        return hardwareCodes;
    }
}

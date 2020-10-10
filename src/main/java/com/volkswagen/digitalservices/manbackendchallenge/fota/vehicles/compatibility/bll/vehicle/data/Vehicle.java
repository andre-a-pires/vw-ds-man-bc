package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.data;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.HardwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.SoftwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;

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

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(name = "vehicle_code_software",
            joinColumns = { @JoinColumn(name="vehicle_id") },
            inverseJoinColumns = {@JoinColumn(name="code_id")})
    private Set<Code> softwareCodes = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "vehicle_code_hardware",
            joinColumns = { @JoinColumn(name="vehicle_id") },
            inverseJoinColumns = {@JoinColumn(name="code_id")})
    private Set<Code> hardwareCodes = new HashSet<>();

    protected Vehicle() {}

    public Vehicle(String vin, Code code) throws InvalidCodeStructureException {
        this.vin = vin;
        this.creationDateTime = LocalDateTime.now();
        if (code instanceof SoftwareCode) {
            this.softwareCodes.add(new SoftwareCode(code.getValue()));
        } else if (code instanceof HardwareCode) {
            this.hardwareCodes.add(new SoftwareCode(code.getValue()));
        } else {
            throw new InvalidCodeStructureException("Unsupported code type!");
        }
    }

    public Vehicle(String vin, Set<? extends Code> codes) {
        this.vin = vin;
        this.creationDateTime = LocalDateTime.now();
        this.softwareCodes = codes.stream()
                .filter(c -> c instanceof SoftwareCode)
                .map(c -> new SoftwareCode(c.getValue()))
                .collect(Collectors.toSet());
        this.hardwareCodes = codes.stream()
                .filter(c -> c instanceof HardwareCode)
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

    public void addSoftwareCode(Code code) {
        this.getSoftwareCodes().add(code);
    }

    public void addHardwareCode(Code code) {
        this.getHardwareCodes().add(code);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle[id=").append(getId())
                .append("vin=").append(getVin()).append(", softwareCodes=");
        getSoftwareCodes().forEach(c -> sb.append(c.getValue() + ","));
        sb.append("; hardwareCodes=");
        getHardwareCodes().forEach(c -> sb.append(c.getValue() + ","));
        sb.append("]");
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setSoftwareCodes(Set<Code> softwareCodes) {
        this.softwareCodes = softwareCodes;
    }

    public void setHardwareCodes(Set<Code> hardwareCodes) {
        this.hardwareCodes = hardwareCodes;
    }
}

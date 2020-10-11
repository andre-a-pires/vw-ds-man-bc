package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.data;

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

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    public void setId(Long id) {
        this.id = id;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(name = "vehicle_code",
            joinColumns = { @JoinColumn(name="vehicle_id") },
            inverseJoinColumns = {@JoinColumn(name="code_id")})
    private Set<Code> codes = new HashSet<>();

    protected Vehicle() {}

    public Vehicle(String vin, Code code) {
        this.vin = vin;
        this.creationDateTime = LocalDateTime.now();
        this.codes.add(code);
    }

    public Vehicle(String vin, Set<Code> codes) {
        this.vin = vin;
        this.creationDateTime = LocalDateTime.now();
        this.codes = codes;
    }

    public String getVin() {
        return this.vin;
    }

    public Set<Code> getSoftwareCodes() {
        return codes.stream().filter(c -> c instanceof SoftwareCode).collect(Collectors.toSet());
    }

    public Set<Code> getHardwareCodes() {
        return codes.stream().filter(c -> c instanceof HardwareCode).collect(Collectors.toSet());
    }

    public void addCode(Code code) {
        this.codes.add(code);
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

}

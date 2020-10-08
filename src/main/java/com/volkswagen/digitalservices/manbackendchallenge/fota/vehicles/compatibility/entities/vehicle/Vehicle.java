package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.vehicle;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.Code;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="vehicle")
public class Vehicle {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vin;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @OneToMany
    private Set<Code> softwareCodes;

    @OneToMany
    private Set<Code> hardwareCodes;

    protected Vehicle() {}

    public Vehicle(String vin) {
        this.vin = vin;
        this.creationDateTime = LocalDateTime.now();
    }

    public String getVin() {
        return this.vin;
    }

}

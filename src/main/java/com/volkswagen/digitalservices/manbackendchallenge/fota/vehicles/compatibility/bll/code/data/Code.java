package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.data.Vehicle;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Inheritance
@DiscriminatorColumn(name="code_type")
@Table(name = "code", indexes = {@Index(name = "index_code_value", unique = true, columnList = "value")})
public abstract class Code {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    @ManyToMany(targetEntity = Vehicle.class)
    private Set<Vehicle> vehicles;

    // ORM usage
    protected Code() {}

    public Code(String value) {
        this.value = value;
        this.creationDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String code) {
        this.value = code;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("FeatureEntity[")
                .append("id=").append(this.getId())
                .append(", value=").append(this.getValue())
                .append(", creationDateTime=").append(this.getCreationDate())
                .append("]")
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (! (obj instanceof Code)) {
            return false;
        }

        Code testObj = (Code) obj;

        return this.getValue().equals(testObj.getValue());
    }

    @Override
    public int hashCode() {
        return 31 * this.value.hashCode();
    }
}

package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.persistence;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="fota_vehicles_compatibility_feature")
public class Feature {
    @Id
    @Column
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

    // ORM usage
    protected Feature() {}

    public Feature(String code) {
        this.code = code;
        this.creationDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("FeatureEntity[")
                .append("id=").append(this.getId())
                .append(", code=").append(this.getCode())
                .append(", creationDateTime=").append(this.getCreationDate())
                .append("]")
                .toString();
    }
}

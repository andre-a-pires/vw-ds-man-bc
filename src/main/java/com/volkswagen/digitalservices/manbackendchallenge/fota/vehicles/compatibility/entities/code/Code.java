package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance
@DiscriminatorColumn(name="code_type")
@Table(name="fota_vehicles_compatibility_code")
public abstract class Code {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

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

}

package com.example.mariadbservice.entity;

import com.example.mariadbservice.model.FuelType;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Car")
@Data
@NoArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "carbrandid", referencedColumnName = "id", insertable = false, updatable = false)
//    @JsonBackReference
    private CarBrandEntity carBrand;

    private String registrationNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "yearmodelid", referencedColumnName = "id", insertable = false, updatable = false)
    private YearModelEntity yearModel;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private int availableSeats;
    private double price;

}

package com.onerivet.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[AddOn]", schema = "[Ref]")
@Data
public class AddOn {

    @Id
    @Column(name = "[AddOnId]")
    private Integer addOnId;

    @Column(name = "[AddOn]")
    private String addOn;

    @Column(name = "[VehicleTypeId]")
    private Integer vehicleTypeId;

    @Column(name = "[CoverageId]")
    private Integer coverageId;

    @Column(name = "[BasePrice]")
    private BigDecimal basePrice;

    @Column(name = "[CreatedDate]")
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;
}

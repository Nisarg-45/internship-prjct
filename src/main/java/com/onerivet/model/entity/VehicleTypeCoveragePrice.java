package com.onerivet.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[VehicleTypeCoveragePrice]", schema = "[Ref]")
@Data
public class VehicleTypeCoveragePrice {

    @Id
   
    @Column(name = "[VehicleTypeCoveragePriceId]", nullable = false)
    private Integer id;

    @Column(name = "[VehicleTypeId]",nullable = false)
    private Integer vehicleTypeId;

    @Column(name = "[CoverageId]",nullable = false)
    private Integer coverageId;

    @Column(name = "[BasePrice]",nullable = false)
    private BigDecimal basePrice;

    @Column(name = "[CreatedDate]")
    private LocalDateTime createdDate;
}

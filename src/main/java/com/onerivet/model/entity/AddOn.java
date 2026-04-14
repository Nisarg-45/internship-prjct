package com.onerivet.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "[AddOn]", schema = "[Ref]")
@Getter
@Setter
public class AddOn {
 
    @Id
    @Column(name = "[AddOnId]", nullable = false)
    private Integer addOnId;

    @Column(name = "[AddOn]", nullable = false)
    private String addOn;

    @ManyToOne
    @JoinColumn(name = "[VehicleTypeId]", nullable = false)
    private VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "[CoverageId]", nullable = false)
    private Coverage coverage;

    @Column(name = "[BasePrice]", nullable = false)
    private BigDecimal basePrice;

    @Column(name = "[CreatedDate]")
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;
}
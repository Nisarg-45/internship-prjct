package com.onerivet.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[TemplateCoverage]", schema = "[Template]")
@Data
public class TemplateCoverage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "[TemplateCoverageId]")
    private String templateCoverageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "[TemplatePlanId]", nullable = false)
    private TemplatePlan templatePlan;
    
    @Column(name = "[CoverageTypeCatalogId]")
    private Integer coverageTypeCatalogId;


    @Column(name = "[BodilyInjuredLimitPerPerson]")
    private BigDecimal bodilyInjuredLimitPerPerson;

    @Column(name = "[BodilyInjuredLimitPerAccident]")
    private BigDecimal bodilyInjuredLimitPerAccident;

    @Column(name = "[PropertyDamageLimit]")
    private BigDecimal propertyDamageLimit;

    @Column(name = "[CombinedLimit]")
    private BigDecimal combinedLimit;

    @Column(name = "[CreatedById]")
    private String createdById;

    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[ModifiedById]")
    private String modifiedById;

    @Column(name = "[ModifiedDate]")
    private LocalDateTime modifiedDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;
}
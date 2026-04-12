package com.onerivet.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[CoverageComponentCatalog]", schema = "[Ref]")
@Data
public class CoverageComponentCatalog {

    @Id
    @Column(name = "[CoverageComponentCatalogId]")
    private Integer id;

    @Column(name = "[CoverageTypeId]")
    private Integer coverageTypeId;

    @Column(name = "[CoverageComponentId]")
    private Integer coverageComponentId;
    
    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;
}
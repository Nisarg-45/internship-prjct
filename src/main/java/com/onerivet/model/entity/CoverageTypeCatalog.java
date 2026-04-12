package com.onerivet.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[CoverageTypeCatalog]", schema = "[Ref]")
@Data
public class CoverageTypeCatalog {

    @Id
    @Column(name = "[CoverageTypeCatalogId]")
    private Integer coverageTypeCatalogId;

    @Column(name = "[CoverageId]", nullable = false)
    private Integer coverageId;

    @Column(name = "[CoverageTypeId]", nullable = false)
    private Integer coverageTypeId;
}
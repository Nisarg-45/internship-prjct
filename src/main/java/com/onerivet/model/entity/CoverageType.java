package com.onerivet.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[CoverageType]", schema = "[Ref]")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverageType {

    @Id
    @Column(name = "[CoverageTypeId]", nullable = false)
    private Integer coverageTypeId;

    @Column(name = "[CoverageType]", nullable = false)
    private String coverageType;

    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;
}
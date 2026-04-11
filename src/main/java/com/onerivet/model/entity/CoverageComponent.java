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
@Table(name = "[CoverageComponent]", schema = "[Ref]")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverageComponent {

    @Id
    @Column(name = "[CoverageComponentId]", nullable = false)
    private Integer coverageComponentId;

    @Column(name = "[Component]", nullable = false)
    private String component;

    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;
}
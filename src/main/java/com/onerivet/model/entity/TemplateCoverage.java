package com.onerivet.model.entity;

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
@Table(name = "[TemplateCoverage]", schema = "[Template]") // adjust schema if needed
@Data
public class TemplateCoverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[TemplateCoverageId]")
    private Integer templateCoverageId;

    // 🔗 MANY TemplateCoverage → ONE Template
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "[TemplateId]", nullable = false)
    private Template template;

    // 🔗 MANY TemplateCoverage → ONE Coverage
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "[CoverageId]", nullable = false)
    private Coverage coverage;

    @Column(name = "[CreatedDate]")
    private LocalDateTime createdDate;
}
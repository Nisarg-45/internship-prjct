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
@Data
@Entity
@Table(name = "[TemplatePlan]", schema = "[Template]")

public class TemplatePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[TemplatePlanId]")
    private Integer templatePlanId;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "[TemplateId]", nullable = false)
    private Template template;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "[PlanId]", nullable = false)
    private Plan plan;

    @Column(name = "[CreatedDate]")
    private LocalDateTime createdDate;
}
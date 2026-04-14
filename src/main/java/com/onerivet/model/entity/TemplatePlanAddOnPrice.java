package com.onerivet.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "[TemplatePlanAddOnPrice]", schema = "[Template]")
@Data
public class TemplatePlanAddOnPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "[TemplatePlanId]")
    private Integer templatePlanId;

    @Column(name = "[AddOnId]")
    private Integer addOnId;

    @Column(name = "[Price]")
    private BigDecimal price;
}
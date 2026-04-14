package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.TemplatePlanAddOnPrice;

public interface TemplatePlanAddOnPriceRepository 
extends JpaRepository<TemplatePlanAddOnPrice, Integer> {

List<TemplatePlanAddOnPrice> findByTemplatePlanId(Integer templatePlanId);
}
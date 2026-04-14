package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.TemplatePlanCoveragePrice;

public interface TemplatePlanCoveragePriceRepository 
extends JpaRepository<TemplatePlanCoveragePrice, Integer> {

List<TemplatePlanCoveragePrice> findByTemplatePlanId(Integer templatePlanId);
}

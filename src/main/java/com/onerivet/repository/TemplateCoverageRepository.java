package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.TemplateCoverage;
import com.onerivet.model.entity.TemplatePlan;

public interface TemplateCoverageRepository extends JpaRepository<TemplateCoverage, Integer> {

	List<TemplateCoverage> findByTemplatePlan(TemplatePlan tp);
	
	@Query("SELECT tc FROM TemplateCoverage tc WHERE tc.templatePlan.templatePlanId = :templatePlanId")
	List<TemplateCoverage> findByTemplatePlanId(Integer templatePlanId);
}
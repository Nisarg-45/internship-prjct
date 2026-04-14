package com.onerivet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.CoverageComponent;

public interface CoverageComponentRepository 
extends JpaRepository<CoverageComponent, Integer> {
	
	//Optional<CoverageComponent> findById(Integer id);
	
	@Query("SELECT cc FROM CoverageComponent cc WHERE cc.coverageComponentId = :id")
	CoverageComponent findComponent(Integer id);
}
package com.onerivet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.CoverageType;

public interface CoverageTypeRepository extends JpaRepository<CoverageType, Integer>{

	//Optional<CoverageType> findById(Integer id);
	
	@Query("SELECT ct FROM CoverageType ct WHERE ct.coverageTypeId = :coverageTypeId")
	CoverageType findByIdCustom(Integer coverageTypeId);
}
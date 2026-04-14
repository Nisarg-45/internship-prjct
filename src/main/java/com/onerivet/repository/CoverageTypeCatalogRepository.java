package com.onerivet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.CoverageTypeCatalog;

public interface CoverageTypeCatalogRepository 
extends JpaRepository<CoverageTypeCatalog, Integer> {
	Optional<CoverageTypeCatalog> findById(Integer id);
	
	
	@Query("SELECT ctc FROM CoverageTypeCatalog ctc WHERE ctc.coverageId = :coverageId")

	List<CoverageTypeCatalog> findByCoverageId(Integer coverageId);
}
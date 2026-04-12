package com.onerivet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.CoverageTypeCatalog;

public interface CoverageTypeCatalogRepository 
extends JpaRepository<CoverageTypeCatalog, Integer> {
	Optional<CoverageTypeCatalog> findById(Integer id);
	
	List<CoverageTypeCatalog> findByCoverageId(Integer coverageId);
}
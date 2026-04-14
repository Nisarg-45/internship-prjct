package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.CoverageComponentCatalog;

public interface CoverageComponentCatalogRepository 
extends JpaRepository<CoverageComponentCatalog, Integer> {



@Query("SELECT ccc FROM CoverageComponentCatalog ccc WHERE ccc.coverageTypeId = :coverageTypeId")
List<CoverageComponentCatalog> findByCoverageTypeId(Integer coverageTypeId);
}
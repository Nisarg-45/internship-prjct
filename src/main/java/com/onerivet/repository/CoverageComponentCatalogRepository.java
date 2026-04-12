package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.CoverageComponentCatalog;

public interface CoverageComponentCatalogRepository 
extends JpaRepository<CoverageComponentCatalog, Integer> {

List<CoverageComponentCatalog> findByCoverageTypeId(Integer coverageTypeId);


}

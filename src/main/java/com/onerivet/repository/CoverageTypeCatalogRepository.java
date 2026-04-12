package com.onerivet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.CoverageTypeCatalog;

public interface CoverageTypeCatalogRepository 
extends JpaRepository<CoverageTypeCatalog, Integer> {
}
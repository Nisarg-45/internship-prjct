package com.onerivet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.CoverageComponent;

public interface CoverageComponentRepository 
extends JpaRepository<CoverageComponent, Integer> {
}

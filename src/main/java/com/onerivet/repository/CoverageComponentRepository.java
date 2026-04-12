package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.CoverageComponent;

public interface CoverageComponentRepository 
extends JpaRepository<CoverageComponent, Integer> {
	
	Optional<CoverageComponent> findById(Integer id);
}

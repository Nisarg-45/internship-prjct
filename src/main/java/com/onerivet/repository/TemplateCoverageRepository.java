package com.onerivet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.TemplateCoverage;

public interface TemplateCoverageRepository extends JpaRepository<TemplateCoverage, Integer> {
}
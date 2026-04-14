package com.onerivet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.Coverage;

public interface CoverageRepository extends JpaRepository<Coverage, Integer> {
   
}
package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.CoverageType;

public interface CoverageTypeRepository extends JpaRepository<CoverageType, Integer>{

	Optional<CoverageType> findById(Integer id);
}

package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.Coverage;

public interface CoverageRepository extends JpaRepository<Coverage, Integer>{

	Optional<Coverage> findById(Integer id);
}

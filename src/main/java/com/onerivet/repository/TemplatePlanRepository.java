package com.onerivet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.TemplatePlan;

public interface TemplatePlanRepository extends JpaRepository<TemplatePlan, Integer> {
}
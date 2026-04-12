package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.TemplatePlan;

public interface TemplatePlanRepository extends JpaRepository<TemplatePlan, Integer> {

	List<TemplatePlan> findByTemplate(Template template);
}
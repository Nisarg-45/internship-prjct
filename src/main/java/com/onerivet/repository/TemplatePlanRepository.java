package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.TemplatePlan;

public interface TemplatePlanRepository extends JpaRepository<TemplatePlan, Integer> {

	List<TemplatePlan> findByTemplate(Template template);
	@Query("SELECT tp FROM TemplatePlan tp WHERE tp.template.templateId = :templateId")
	List<TemplatePlan> findByTemplateId(Integer templateId);
}
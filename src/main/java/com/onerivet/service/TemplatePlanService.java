package com.onerivet.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.model.entity.Plan;
import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.TemplatePlan;
import com.onerivet.repository.PlanRepository;
import com.onerivet.repository.TemplatePlanRepository;
import com.onerivet.repository.TemplateRepo;

@Service
public class TemplatePlanService {

    @Autowired
    private TemplateRepo templateRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private TemplatePlanRepository templatePlanRepository;

    public void addPlansToTemplate(Integer templateId, List<Integer> planIds) {

        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        for (Integer planId : planIds) {

            Plan plan = planRepository.findById(planId)
                    .orElseThrow(() -> new RuntimeException("Plan not found"));

            TemplatePlan tp = new TemplatePlan();
            tp.setTemplate(template);
            tp.setPlan(plan);
            tp.setCreatedDate(LocalDateTime.now());

            templatePlanRepository.save(tp);
        }
    }
}
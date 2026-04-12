package com.onerivet.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.dto.TemplateCoverageListRequestDto;
import com.onerivet.dto.TemplateCoverageRequestDto;
import com.onerivet.model.entity.TemplateCoverage;
import com.onerivet.model.entity.TemplatePlan;
import com.onerivet.repository.TemplateCoverageRepository;
import com.onerivet.repository.TemplatePlanRepository;

@Service
public class TemplateCoverageService {

    @Autowired
    private TemplatePlanRepository templatePlanRepository;

    @Autowired
    private TemplateCoverageRepository templateCoverageRepository;

    public void addCoveragesToTemplate(Integer templatePlanId,
                                       TemplateCoverageListRequestDto request) {

        TemplatePlan templatePlan = templatePlanRepository.findById(templatePlanId)
                .orElseThrow(() -> new RuntimeException("TemplatePlan not found"));

        for (TemplateCoverageRequestDto req : request.getCoverages()) {

            TemplateCoverage tc = new TemplateCoverage();

            tc.setTemplatePlan(templatePlan);
            tc.setCoverageTypeCatalogId(req.getCoverageTypeCatalogId());
            tc.setCreatedDate(LocalDateTime.now());

            tc.setBodilyInjuredLimitPerPerson(req.getBodilyInjuredLimitPerPerson());
            tc.setBodilyInjuredLimitPerAccident(req.getBodilyInjuredLimitPerAccident());
            tc.setPropertyDamageLimit(req.getPropertyDamageLimit());
            tc.setCombinedLimit(req.getCombinedLimit());

            templateCoverageRepository.save(tc);
        }
    }
}
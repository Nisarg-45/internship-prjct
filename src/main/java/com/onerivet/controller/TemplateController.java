package com.onerivet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.PremiumResponseDto;
import com.onerivet.dto.TemplateCoverageListRequestDto;
import com.onerivet.dto.TemplateFullResponseDto;
import com.onerivet.dto.TemplatePlanRequestDto;
import com.onerivet.dto.TemplateRequestDto;
import com.onerivet.dto.TemplateResponseDto;
import com.onerivet.service.AddOnService;
import com.onerivet.service.CoverageComponentService;
import com.onerivet.service.TemplateCoverageService;
import com.onerivet.service.TemplatePlanService;
import com.onerivet.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;
    private final TemplatePlanService templatePlanService;
    private final TemplateCoverageService templateCoverageService;
    private final CoverageComponentService componentService;
    private final AddOnService addOnService;

    // 🔹 1. Create Template
    @PostMapping
    public TemplateResponseDto createTemplate(@RequestBody TemplateRequestDto request) {
        return templateService.createTemplate(request);
    }

    // 🔹 2. Add Plans
    @PostMapping("/{templateId}/plans")
    public String addPlansToTemplate(
            @PathVariable Integer templateId,
            @RequestBody TemplatePlanRequestDto request) {

        templatePlanService.addPlansToTemplate(templateId, request.getPlanIds());
        return "Plans added successfully";
    }

    // 🔹 3. Add Coverages
    @PostMapping("/template-plans/{templatePlanId}/coverages")
    public String addCoveragesToTemplate(
            @PathVariable Integer templatePlanId,
            @RequestBody TemplateCoverageListRequestDto request) {

        templateCoverageService.addCoveragesToTemplate(templatePlanId, request);
        return "Coverages added successfully";
    }

    // 🔹 4. Get Components
    @GetMapping("/coverage-type/{id}/components")
    public List<String> getComponents(@PathVariable Integer id) {
        return componentService.getComponentNames(id);
    }

    // 🔹 5. Get AddOns
    @GetMapping("/add-ons")
    public List<String> getAddOns(
            @RequestParam Integer vehicleTypeId,
            @RequestParam Integer coverageId) {

        return addOnService.getAddOnNames(vehicleTypeId, coverageId);
    }

    // 🔹 6. Full Template API
    @GetMapping("/{templateId}/full")
    public TemplateFullResponseDto getFullTemplate(@PathVariable Integer templateId) {
        return templateService.getFullTemplate(templateId);
    }
    
    @GetMapping("/{templateId}/premium")
    public PremiumResponseDto calculatePremium(@PathVariable Integer templateId) {
        return templateService.calculatePremium(templateId);
    }
}
package com.onerivet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.CoverageComponentResponseDto;
import com.onerivet.dto.CoverageResponseDto;
import com.onerivet.dto.CoverageTypeResponseDto;
import com.onerivet.dto.PlanResponseDto;
import com.onerivet.dto.VehicleTypeResponseDto;
import com.onerivet.service.TemplateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService service;

    @GetMapping("/vehicle-types")
    public List<VehicleTypeResponseDto> getVehicleTypes() {
        return service.getVehicleTypes();
    }

    @GetMapping("/vehicle-types/{id}/plans")
    public List<PlanResponseDto> getPlans(@PathVariable Integer id) {
        return service.getPlansByVehicleType(id);
    }

    @GetMapping("/template-plans/{id}/coverages")
    public List<CoverageResponseDto> getCoverages(@PathVariable Integer id) {
        return service.getCoverages(id);
    }

    @GetMapping("/coverages/{id}/types")
    public List<CoverageTypeResponseDto> getTypes(@PathVariable Integer id) {
        return service.getTypes(id);
    }

    @GetMapping("/coverage-types/{id}/components")
    public List<CoverageComponentResponseDto> getComponents(@PathVariable Integer id) {
        return service.getComponents(id);
    }
}
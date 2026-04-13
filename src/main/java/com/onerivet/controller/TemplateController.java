package com.onerivet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.CoverageComponentResponseDto;
import com.onerivet.dto.CoverageResponseDto;
import com.onerivet.dto.CoverageTypeResponseDto;
import com.onerivet.dto.PlanResponseDto;
import com.onerivet.dto.TemplateCoverageListRequestDto;
import com.onerivet.dto.TemplatePlanRequestDto;
import com.onerivet.dto.VehicleTypeResponseDto;
import com.onerivet.service.TemplateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Template APIs", description = "APIs for Insurance Template Flow")
public class TemplateController {

    private final TemplateService service;

   
    @Operation(summary = "Get all vehicle types")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vehicle types fetched successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/vehicle-types")
    public List<VehicleTypeResponseDto> getVehicleTypes() {
        return service.getVehicleTypes();
    }

  
    @Operation(summary = "Get plans by vehicle type")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Plans fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Vehicle type not found")
    })
    @GetMapping("/vehicle-types/{vehicleTypeId}/plans")
    public List<PlanResponseDto> getPlans(
            @Parameter(description = "Vehicle Type ID", example = "1")
            @PathVariable Integer vehicleTypeId) {

        return service.getPlansByVehicleType(vehicleTypeId);
    }

   
    @Operation(summary = "Get coverages by template plan")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Coverages fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Template plan not found")
    })
    @GetMapping("/template-plans/{templatePlanId}/coverages")
    public List<CoverageResponseDto> getCoverages(
            @Parameter(description = "Template Plan ID", example = "1")
            @PathVariable Integer templatePlanId) {

        return service.getCoverages(templatePlanId);
    }

  
    @Operation(summary = "Get coverage types by coverage")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Coverage types fetched successfully")
    })
    @GetMapping("/coverages/{coverageId}/types")
    public List<CoverageTypeResponseDto> getTypes(
            @Parameter(description = "Coverage ID", example = "1")
            @PathVariable Integer coverageId) {

        return service.getTypes(coverageId);
    }

    
    @Operation(summary = "Get components by coverage type")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Components fetched successfully")
    })
    @GetMapping("/coverage-types/{coverageTypeId}/components")
    public List<CoverageComponentResponseDto> getComponents(
            @Parameter(description = "Coverage Type ID", example = "3")
            @PathVariable Integer coverageTypeId) {

        return service.getComponents(coverageTypeId);
    }

 
    @Operation(summary = "Add plans to template")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Plans added successfully"),
        @ApiResponse(responseCode = "404", description = "Template not found")
    })
    @PostMapping("/templates/{templateId}/plans")
    public String addPlans(
            @Parameter(description = "Template ID", example = "1")
            @PathVariable Integer templateId,
            @RequestBody TemplatePlanRequestDto request) {

        service.addPlansToTemplate(templateId, request.getPlanIds());
        return "Plans added successfully";
    }

   
    @Operation(summary = "Add coverages to template plan")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Coverages added successfully"),
        @ApiResponse(responseCode = "404", description = "Template plan not found")
    })
    @PostMapping("/template-plans/{templatePlanId}/coverages")
    public String addCoverages(
            @Parameter(description = "Template Plan ID", example = "1")
            @PathVariable Integer templatePlanId,
            @RequestBody TemplateCoverageListRequestDto request) {

        service.addCoverages(templatePlanId, request.getCoverages());
        return "Coverages added successfully";
    }
}
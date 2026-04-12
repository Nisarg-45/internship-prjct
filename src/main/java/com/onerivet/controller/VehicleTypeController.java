package com.onerivet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.PlanResponseDto;
import com.onerivet.dto.VehicleTypeResponseDto;
import com.onerivet.service.TemplateService;
import com.onerivet.service.VehicleTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vehicle-types")
@RequiredArgsConstructor
public class VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;
    private final TemplateService templateService;

    @GetMapping
    public List<VehicleTypeResponseDto> getAllVehicleTypes() {
        return vehicleTypeService.getAllVehicleTypes();
    }
    
    @GetMapping("{vehicleTypeId}/plans")
    public List<PlanResponseDto> getPlansByVehicleType(
            @PathVariable Integer vehicleTypeId) {

        return templateService.getPlansByVehicleType(vehicleTypeId);
    }
}
package com.onerivet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.PlanResponseDto;
import com.onerivet.service.PlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vehicle-types")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping("/{vehicleTypeId}/plans")
    public ResponseEntity<List<PlanResponseDto>> getPlans(@PathVariable Integer vehicleTypeId) {

        List<PlanResponseDto> data = planService.getPlansByVehicleType(vehicleTypeId);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        } 

        return ResponseEntity.ok(data);
    }
}
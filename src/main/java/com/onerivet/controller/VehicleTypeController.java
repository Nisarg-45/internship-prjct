package com.onerivet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.VehicleTypeResponseDto;
import com.onerivet.service.VehicleTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vehicle-types")
@RequiredArgsConstructor

public class VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    
    @GetMapping
    public ResponseEntity<List<VehicleTypeResponseDto>> getVehicleTypes() {

        List<VehicleTypeResponseDto> data = vehicleTypeService.getVehicleTypes();

       
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data); 
    }
}
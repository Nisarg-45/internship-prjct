
package com.onerivet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.CoverageResponseDto;
import com.onerivet.service.CoverageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/coverages")
@RequiredArgsConstructor
public class CoverageController {

    private final CoverageService coverageService;

    @GetMapping("/{vehicleTypeId}")
    public ResponseEntity<List<CoverageResponseDto>> getCoverages(
            @PathVariable Integer vehicleTypeId) {

        List<CoverageResponseDto> data =
                coverageService.getCoverages(vehicleTypeId);

        if (data.isEmpty()) {
            return ResponseEntity.noContent().build(); // better than 404
        }

        return ResponseEntity.ok(data);
    }
}

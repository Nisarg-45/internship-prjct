package com.onerivet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.CoverageTypeResponseDto;
import com.onerivet.service.CoverageTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/coverages")
@RequiredArgsConstructor
public class CoverageTypeController {

    private final CoverageTypeService coverageTypeService;

    @GetMapping("/{coverageId}/types")
    public ResponseEntity<List<CoverageTypeResponseDto>> getTypes(@PathVariable Integer coverageId) {

        List<CoverageTypeResponseDto> data = coverageTypeService.getTypes(coverageId);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
    }
}
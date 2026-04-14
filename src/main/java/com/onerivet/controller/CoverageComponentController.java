package com.onerivet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.dto.CoverageComponentResponseDto;
import com.onerivet.service.CoverageComponentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/coverage-types")
@RequiredArgsConstructor
public class CoverageComponentController {

    private final CoverageComponentService coverageComponentService;

    @GetMapping("/{coverageTypeId}/components")
    public ResponseEntity<List<CoverageComponentResponseDto>> getComponents(@PathVariable Integer coverageTypeId) {

        List<CoverageComponentResponseDto> data = coverageComponentService.getComponents(coverageTypeId);

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
    }
}
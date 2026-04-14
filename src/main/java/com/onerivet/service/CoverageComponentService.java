package com.onerivet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.onerivet.dto.CoverageComponentResponseDto;
import com.onerivet.model.entity.CoverageComponent;
import com.onerivet.model.entity.CoverageComponentCatalog;
import com.onerivet.repository.CoverageComponentCatalogRepository;
import com.onerivet.repository.CoverageComponentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoverageComponentService {

    private final CoverageComponentCatalogRepository coverageComponentCatalogRepository;
    private final CoverageComponentRepository coverageComponentRepository;

    public List<CoverageComponentResponseDto> getComponents(Integer coverageTypeId) {

        List<CoverageComponentResponseDto> result = new ArrayList<>();

        for (CoverageComponentCatalog ccc :
                coverageComponentCatalogRepository.findByCoverageTypeId(coverageTypeId)) {

            CoverageComponent c =
                    coverageComponentRepository.findById(ccc.getCoverageComponentId())
                            .orElseThrow();

            result.add(new CoverageComponentResponseDto(
                    c.getCoverageComponentId(),
                    c.getComponent()
            ));
        }

        return result;
    }
} 
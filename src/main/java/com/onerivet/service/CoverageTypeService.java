package com.onerivet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.onerivet.dto.CoverageTypeResponseDto;
import com.onerivet.model.entity.CoverageType;
import com.onerivet.model.entity.CoverageTypeCatalog;
import com.onerivet.repository.CoverageTypeCatalogRepository;
import com.onerivet.repository.CoverageTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoverageTypeService {

    private final CoverageTypeCatalogRepository coverageTypeCatalogRepository;
    private final CoverageTypeRepository coverageTypeRepository;

    public List<CoverageTypeResponseDto> getTypes(Integer coverageId) {

        List<CoverageTypeResponseDto> result = new ArrayList<>();

        for (CoverageTypeCatalog ctc :
                coverageTypeCatalogRepository.findByCoverageId(coverageId)) {

            CoverageType type = coverageTypeRepository.findById(ctc.getCoverageTypeId())
                    .orElseThrow();

            result.add(new CoverageTypeResponseDto(
                    type.getCoverageTypeId(),
                    type.getCoverageType()
            ));
        }

        return result;
    }
}

 
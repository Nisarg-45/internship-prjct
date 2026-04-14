package com.onerivet.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.onerivet.dto.CoverageResponseDto;
import com.onerivet.model.entity.VehicleTypeCoveragePrice;
import com.onerivet.repository.CoverageRepository;
import com.onerivet.repository.VehicleTypeCoveragePriceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoverageService {

    private final CoverageRepository coverageRepository;
    private final VehicleTypeCoveragePriceRepository priceRepository;

    public List<CoverageResponseDto> getCoverages(Integer vehicleTypeId) {
        
        
        Map<Integer, BigDecimal> prices = priceRepository.findByVehicleTypeId(vehicleTypeId)
                .stream()
                .collect(Collectors.toMap(
                        VehicleTypeCoveragePrice::getCoverageId, 
                        VehicleTypeCoveragePrice::getBasePrice
                ));

       
        return coverageRepository.findAll().stream().map(c -> new CoverageResponseDto(
                c.getCoverageId(),
                c.getCoverage(),
                prices.getOrDefault(c.getCoverageId(), BigDecimal.ZERO)
        )).toList();
    }
}

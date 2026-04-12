package com.onerivet.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.model.entity.AddOn;
import com.onerivet.model.entity.TemplateCoverage;
import com.onerivet.repository.AddOnRepository;

@Service
public class PricingService {

    @Autowired
    private AddOnRepository addOnRepository;

    public double calculateLiability(TemplateCoverage tc) {

        if (tc.getCombinedLimit() != null) {
            return tc.getCombinedLimit().doubleValue() * 0.02;
        }

        double sum = 0;

        if (tc.getBodilyInjuredLimitPerPerson() != null)
            sum += tc.getBodilyInjuredLimitPerPerson().doubleValue();

        if (tc.getBodilyInjuredLimitPerAccident() != null)
            sum += tc.getBodilyInjuredLimitPerAccident().doubleValue();

        if (tc.getPropertyDamageLimit() != null)
            sum += tc.getPropertyDamageLimit().doubleValue();

        return sum * 0.01;
    }

    public double calculateCollision(Integer coverageTypeCatalogId) {

        // simple logic
        if (coverageTypeCatalogId == 3) { // standard
            return 500;
        } else if (coverageTypeCatalogId == 4) { // deductible
            return 300;
        }

        return 0;
    }

    public double calculateComprehensive(Integer coverageTypeCatalogId) {

        if (coverageTypeCatalogId == 5) {
            return 700;
        } else if (coverageTypeCatalogId == 6) {
            return 1000;
        }

        return 0;
    }

    public double calculateAddOns(Integer vehicleTypeId, Integer coverageId) {

        List<AddOn> addOns =
                addOnRepository.findByVehicleTypeIdAndCoverageId(vehicleTypeId, coverageId);

        return addOns.stream()
                .map(AddOn::getBasePrice)
                .filter(price -> price != null)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }
}
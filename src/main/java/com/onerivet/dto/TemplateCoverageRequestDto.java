package com.onerivet.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TemplateCoverageRequestDto {

    private Integer coverageTypeCatalogId;

    private BigDecimal bodilyInjuredLimitPerPerson;
    private BigDecimal bodilyInjuredLimitPerAccident;
    private BigDecimal propertyDamageLimit;
    private BigDecimal combinedLimit;
	
}

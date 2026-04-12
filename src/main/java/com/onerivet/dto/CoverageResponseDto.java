package com.onerivet.dto;

import lombok.Builder;
import lombok.Data;

@Data
 @Builder
public class CoverageResponseDto {
	
	 private Integer coverageId;
	    private String coverageName;

//    private Integer coverageTypeCatalogId;
//    private String coverageName;
//
//    private BigDecimal bodilyInjuredLimitPerPerson;
//    private BigDecimal bodilyInjuredLimitPerAccident;
//    private BigDecimal propertyDamageLimit;
//    private BigDecimal combinedLimit;
//
//    private List<String> components;
//    private List<String> addOns;
}

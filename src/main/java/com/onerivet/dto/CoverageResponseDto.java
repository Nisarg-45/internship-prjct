package com.onerivet.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoverageResponseDto {
    private Integer coverageId;
    private String coverageName;
    private BigDecimal basePrice;
}
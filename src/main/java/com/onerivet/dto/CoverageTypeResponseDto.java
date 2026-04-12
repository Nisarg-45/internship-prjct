package com.onerivet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoverageTypeResponseDto {
    private Integer coverageTypeId;
    private String coverageTypeName;
}
package com.onerivet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoverageComponentResponseDto {
    private Integer componentId;
    private String componentName;
}
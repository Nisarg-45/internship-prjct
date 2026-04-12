package com.onerivet.dto;

import java.util.List;

import lombok.Data;
@Data
public class TemplateCoverageListRequestDto {
    private List<TemplateCoverageRequestDto> coverages;
}
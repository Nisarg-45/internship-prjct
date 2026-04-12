package com.onerivet.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemplateFullResponseDto {
    private Integer templateId;
    private String templateName;
    private String vehicleType;
    private List<PlanResponseDto> plans;
}
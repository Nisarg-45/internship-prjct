package com.onerivet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanResponseDto {
    private Integer planId;
    private String planName;
   
}
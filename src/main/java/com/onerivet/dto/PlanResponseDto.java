package com.onerivet.dto;

//import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanResponseDto {
    private Integer planId;
    private String planName;
   // private List<CoverageResponseDto> coverages;
}

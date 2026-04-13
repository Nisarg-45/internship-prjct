package com.onerivet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PricingResponseDto {

    private Double liabilityPremium;
    private Double collisionPremium;
    private Double comprehensivePremium;
   
    private Double totalPremium;
	
}
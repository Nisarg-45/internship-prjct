package com.onerivet.dto;

import lombok.Data;

@Data
public class PremiumResponseDto {

    private Double liabilityPremium;
    private Double collisionPremium;
    private Double comprehensivePremium;
    private Double addOnPremium;
    private Double totalPremium;
	
}
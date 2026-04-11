package com.onerivet.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemplateResponseDto {

    private Integer templateId;
    private String templateName;
    private String vehicleType;
    private String status;
    private LocalDateTime createdDate;
	
	
}
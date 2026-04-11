package com.onerivet.dto;

import lombok.Data;

@Data
public class TemplateRequestDto {
	  private String templateName;
	    private Integer vehicleTypeId;
		public String getTemplateName() {
			return templateName;
		}
		public void setTemplateName(String templateName) {
			this.templateName = templateName;
		}
		public Integer getVehicleTypeId() {
			return vehicleTypeId;
		}
		public void setVehicleTypeId(Integer vehicleTypeId) {
			this.vehicleTypeId = vehicleTypeId;
		}
		
	    
	    
}

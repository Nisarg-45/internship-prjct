package com.onerivet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.dto.TemplateCoverageListRequestDto;
import com.onerivet.dto.TemplateCoverageRequestDto;
import com.onerivet.dto.TemplateRequestDto;
import com.onerivet.dto.TemplateResponseDto;
import com.onerivet.model.entity.CoverageComponent;
import com.onerivet.model.entity.CoverageComponentCatalog;
import com.onerivet.model.entity.CoverageTypeCatalog;
import com.onerivet.model.entity.Plan;
import com.onerivet.model.entity.Status;
import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.TemplateCoverage;
import com.onerivet.model.entity.TemplatePlan;
import com.onerivet.model.entity.VehicleType;
import com.onerivet.repository.CoverageComponentCatalogRepository;
import com.onerivet.repository.CoverageComponentRepository;
import com.onerivet.repository.CoverageRepository;
import com.onerivet.repository.CoverageTypeCatalogRepository;
import com.onerivet.repository.PlanRepository;
import com.onerivet.repository.StatusRepository;
import com.onerivet.repository.TemplateCoverageRepository;
import com.onerivet.repository.TemplatePlanRepository;
import com.onerivet.repository.TemplateRepo;
import com.onerivet.repository.VehicleTypeRepository;

@Service


public class TemplateService {

	@Autowired
	private TemplateRepo templateRepository;
	
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	
	private TemplatePlanRepository templatePlanRepository;
	
	@Autowired
	private CoverageRepository coverageRepository;
	
	@Autowired
	private TemplateCoverageRepository templateCoverageRepository;
	
	@Autowired
	private CoverageTypeCatalogRepository coverageTypeCatalogRepository;
	
	@Autowired
	private CoverageComponentCatalogRepository coverageComponentCatalogRepository;
	
	@Autowired
	private CoverageComponentRepository coverageComponentRepository;
	
	public TemplateResponseDto createTemplate(TemplateRequestDto request) {

	    
		VehicleType vehicleType = vehicleTypeRepository.findById(request.getVehicleTypeId())
	            .orElseThrow(() -> new RuntimeException("VehicleType not found"));

	    Status status = statusRepository.findById(1) // DRAFT
	            .orElseThrow(() -> new RuntimeException("Status not found"));

	    Template template = new Template();
	    template.setTemplateName(request.getTemplateName());
	    template.setVehicleType(vehicleType);
	    template.setStatus(status);
	    template.setCreatedDate(LocalDateTime.now());

	    Template saved = templateRepository.save(template);

	    // 🔥 Convert Entity → ResponseDTO
	    return TemplateResponseDto.builder()
	            .templateId(saved.getTemplateId())
	            .templateName(saved.getTemplateName())
	            .vehicleType(saved.getVehicleType().getVehicle())
	            .status(saved.getStatus().getStatus())
	            .createdDate(saved.getCreatedDate())
	            .build();
	}
	
	
	public void addPlansToTemplate(Integer templateId, List<Integer> planIds) {

	    // 1. Get Template
	    Template template = templateRepository.findById(templateId)
	            .orElseThrow(() -> new RuntimeException("Template not found"));

	    for (Integer planId : planIds) {

	        // 2. Get Plan
	        Plan plan = planRepository.findById(planId)
	                .orElseThrow(() -> new RuntimeException("Plan not found"));

	        // 3. Create TemplatePlan
	        TemplatePlan templatePlan = new TemplatePlan();
	        templatePlan.setTemplate(template);
	        templatePlan.setPlan(plan);
	        templatePlan.setCreatedDate(LocalDateTime.now());

	        // 4. Save
	        templatePlanRepository.save(templatePlan);
	    }
	}
	
	public void addCoveragesToTemplate(Integer templatePlanId, TemplateCoverageListRequestDto request) {

	    TemplatePlan templatePlan = templatePlanRepository.findById(templatePlanId)
	            .orElseThrow(() -> new RuntimeException("TemplatePlan not found"));

	    for (TemplateCoverageRequestDto req : request.getCoverages()) {

	        TemplateCoverage tc = new TemplateCoverage();

	        tc.setTemplatePlan(templatePlan);
	        tc.setCoverageTypeCatalogId(req.getCoverageTypeCatalogId());
	        tc.setCreatedDate(LocalDateTime.now());

	        // Liability fields (optional)
	        tc.setBodilyInjuredLimitPerPerson(req.getBodilyInjuredLimitPerPerson());
	        tc.setBodilyInjuredLimitPerAccident(req.getBodilyInjuredLimitPerAccident());
	        tc.setPropertyDamageLimit(req.getPropertyDamageLimit());
	        tc.setCombinedLimit(req.getCombinedLimit());

	        templateCoverageRepository.save(tc);
	    }
	}
	

	
	public List<CoverageComponent> getComponentsByCoverageTypeCatalogId(Integer coverageTypeCatalogId) {

	    // 1. Get coverage type
	    CoverageTypeCatalog typeCatalog = coverageTypeCatalogRepository
	            .findById(coverageTypeCatalogId)
	            .orElseThrow(() -> new RuntimeException("Invalid coverage type"));

	    Integer coverageTypeId = typeCatalog.getCoverageTypeId();

	    // 2. Get allowed components
	    List<CoverageComponentCatalog> componentCatalogs =
	            coverageComponentCatalogRepository.findByCoverageTypeId(coverageTypeId);

	    List<CoverageComponent> components = new ArrayList<>();

	    for (CoverageComponentCatalog ccc : componentCatalogs) {
	        CoverageComponent component = coverageComponentRepository
	                .findById(ccc.getCoverageComponentId())
	                .orElseThrow();
	        components.add(component);
	    }

	    return components;
	}
}

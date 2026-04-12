package com.onerivet.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.dto.CoverageComponentResponseDto;
import com.onerivet.dto.CoverageResponseDto;
import com.onerivet.dto.CoverageTypeResponseDto;
import com.onerivet.dto.PlanResponseDto;
import com.onerivet.dto.VehicleTypeResponseDto;
import com.onerivet.model.entity.Coverage;
import com.onerivet.model.entity.CoverageComponent;
import com.onerivet.model.entity.CoverageComponentCatalog;
import com.onerivet.model.entity.CoverageType;
import com.onerivet.model.entity.CoverageTypeCatalog;
import com.onerivet.model.entity.Plan;
import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.TemplateCoverage;
import com.onerivet.model.entity.TemplatePlan;
import com.onerivet.model.entity.VehicleType;
import com.onerivet.repository.CoverageComponentCatalogRepository;
import com.onerivet.repository.CoverageComponentRepository;
import com.onerivet.repository.CoverageRepository;
import com.onerivet.repository.CoverageTypeCatalogRepository;
import com.onerivet.repository.CoverageTypeRepository;
import com.onerivet.repository.PlanRepository;
import com.onerivet.repository.StatusRepository;
import com.onerivet.repository.TemplateCoverageRepository;
import com.onerivet.repository.TemplatePlanRepository;
import com.onerivet.repository.TemplateRepository;
import com.onerivet.repository.VehicleTypeRepository;

@Service

public class TemplateService {
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	@Autowired
	private TemplateRepository templateRepository;
	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private TemplatePlanRepository templatePlanRepository;
	@Autowired
	private TemplateCoverageRepository templateCoverageRepository;
	@Autowired
	private CoverageRepository coverageRepository;
	@Autowired
	private CoverageTypeRepository coverageTypeRepository;
	@Autowired
	private CoverageTypeCatalogRepository coverageTypeCatalogRepository;
	@Autowired
	private CoverageComponentRepository coverageComponentRepository;
	@Autowired
	private CoverageComponentCatalogRepository coverageComponentCatalogRepository;
	@Autowired
	private StatusRepository statusRepository;


	public List<VehicleTypeResponseDto> getVehicleTypes() {
		List<VehicleTypeResponseDto> result = new ArrayList<>();
		for (VehicleType v : vehicleTypeRepository.findAll()) {
			result.add(new VehicleTypeResponseDto(v.getVehicleTypeId(), v.getVehicle()));
		}
		return result;
	}

	
	public List<PlanResponseDto> getPlansByVehicleType(Integer vehicleTypeId) {

		VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId).orElseThrow();

		List<Template> templates = templateRepository.findByVehicleType(vehicleType);

		Map<Integer, PlanResponseDto> map = new LinkedHashMap<>();

		for (Template t : templates) {
			for (TemplatePlan tp : templatePlanRepository.findByTemplate(t)) {
				Plan p = tp.getPlan();

				map.putIfAbsent(p.getPlanId(), PlanResponseDto.builder().planId(p.getPlanId()).planName(p.getPlan())
						// .coverages(null)
						.build());
			}
		}

		return new ArrayList<>(map.values());
	}

	public List<CoverageResponseDto> getCoverages(Integer templatePlanId) {

	    TemplatePlan templatePlan = templatePlanRepository.findById(templatePlanId)
	            .orElseThrow(() -> new RuntimeException("TemplatePlan not found"));

	    Map<Integer, CoverageResponseDto> map = new LinkedHashMap<>();

	    List<TemplateCoverage> templateCoverages =
	            templateCoverageRepository.findByTemplatePlan(templatePlan);

	    for (TemplateCoverage tc : templateCoverages) {

	        CoverageTypeCatalog catalog =
	                coverageTypeCatalogRepository.findById(tc.getCoverageTypeCatalogId())
	                        .orElseThrow();

	        Coverage coverage =
	                coverageRepository.findById(catalog.getCoverageId())
	                        .orElseThrow();

	        
	        map.putIfAbsent(
	                coverage.getCoverageId(),
	                new CoverageResponseDto(
	                        coverage.getCoverageId(),
	                        coverage.getCoverage()
	                )
	        );
	    }

	    return new ArrayList<>(map.values());
	}
	
	public List<CoverageTypeResponseDto> getTypes(Integer coverageId) {

		List<CoverageTypeResponseDto> result = new ArrayList<>();

		for (CoverageTypeCatalog ctc : coverageTypeCatalogRepository.findByCoverageId(coverageId)) {

			CoverageType type = coverageTypeRepository.findById(ctc.getCoverageTypeId()).orElseThrow();

			result.add(new CoverageTypeResponseDto(type.getCoverageTypeId(), type.getCoverageType()));
		}

		return result;
	}

	
	public List<CoverageComponentResponseDto> getComponents(Integer coverageTypeId) {

		List<CoverageComponentResponseDto> result = new ArrayList<>();

		for (CoverageComponentCatalog ccc : coverageComponentCatalogRepository.findByCoverageTypeId(coverageTypeId)) {

			CoverageComponent c = coverageComponentRepository.findById(ccc.getCoverageComponentId()).orElseThrow();

			result.add(new CoverageComponentResponseDto(c.getCoverageComponentId(), c.getComponent()));
		}

		return result;
	}
}
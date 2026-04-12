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
import com.onerivet.dto.PremiumResponseDto;
import com.onerivet.dto.TemplateRequestDto;
import com.onerivet.dto.TemplateResponseDto;
import com.onerivet.model.entity.Coverage;
import com.onerivet.model.entity.CoverageComponent;
import com.onerivet.model.entity.CoverageComponentCatalog;
import com.onerivet.model.entity.CoverageType;
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
import com.onerivet.repository.CoverageTypeRepository;
import com.onerivet.repository.StatusRepository;
import com.onerivet.repository.TemplateCoverageRepository;
import com.onerivet.repository.TemplatePlanRepository;
import com.onerivet.repository.TemplateRepopository;
import com.onerivet.repository.VehicleTypeRepository;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepopository templateRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private TemplatePlanRepository templatePlanRepository;

    @Autowired
    private TemplateCoverageRepository templateCoverageRepository;

    @Autowired
    private CoverageRepository coverageRepository;

    @Autowired
    private CoverageTypeCatalogRepository coverageTypeCatalogRepository;

    @Autowired
    private CoverageComponentService componentService;

    @Autowired
    private AddOnService addOnService;
    
    @Autowired
    private PricingService pricingService;
    
    @Autowired
    private CoverageTypeRepository coverageTypeRepository;
    
    @Autowired
private CoverageComponentCatalogRepository coverageComponentCatalogRepository;
    
    @Autowired
    private CoverageComponentRepository coverageComponentRepository;
    
    public List<CoverageResponseDto> getCoveragesByTemplatePlan(Integer templatePlanId) {

        // 1. Get TemplatePlan
        TemplatePlan templatePlan = templatePlanRepository.findById(templatePlanId)
                .orElseThrow(() -> new RuntimeException("TemplatePlan not found"));

        // 2. Get TemplateCoverages
        List<TemplateCoverage> templateCoverages =
                templateCoverageRepository.findByTemplatePlan(templatePlan);

        Map<Integer, CoverageResponseDto> map = new LinkedHashMap<>();

        // 3. Loop
        for (TemplateCoverage tc : templateCoverages) {

            // 4. Get CoverageTypeCatalog
            CoverageTypeCatalog typeCatalog =
                    coverageTypeCatalogRepository.findById(tc.getCoverageTypeCatalogId())
                            .orElseThrow(() -> new RuntimeException("Invalid CoverageTypeCatalog"));

            // 5. Get Coverage
            Coverage coverage =
                    coverageRepository.findById(typeCatalog.getCoverageId())
                            .orElseThrow(() -> new RuntimeException("Coverage not found"));

         
            
        }

        return new ArrayList<>(map.values());
    }
    
    public List<CoverageTypeResponseDto> getCoverageTypes(Integer coverageId) {

        List<CoverageTypeCatalog> catalogs =
                coverageTypeCatalogRepository.findByCoverageId(coverageId);

        List<CoverageTypeResponseDto> result = new ArrayList<>();

        for (CoverageTypeCatalog ctc : catalogs) {

            CoverageType type = coverageTypeRepository.findById(ctc.getCoverageTypeId())
                    .orElseThrow(() -> new RuntimeException("CoverageType not found"));

            CoverageTypeResponseDto dto =
                    new CoverageTypeResponseDto(type.getCoverageTypeId(), type.getCoverageType());

            result.add(dto);
        }

        return result;
    }
    public List<PlanResponseDto> getPlansByVehicleType(Integer vehicleTypeId) {

        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow(() -> new RuntimeException("VehicleType not found"));

        List<Template> templates = templateRepository.findByVehicleType(vehicleType);

        List<PlanResponseDto> result = new ArrayList<>();

        for (Template template : templates) {

            List<TemplatePlan> templatePlans =
                    templatePlanRepository.findByTemplate(template);

            for (TemplatePlan tp : templatePlans) {

                Plan plan = tp.getPlan();

                PlanResponseDto dto = PlanResponseDto.builder()
                        .planId(plan.getPlanId())
                        .planName(plan.getPlan())
                        //.coverages(null)
                        .build();

                result.add(dto);
            }
        }

        return result;
    }
    
    public List<CoverageComponentResponseDto> getComponentsByCoverageType(Integer coverageTypeId) {

        List<CoverageComponentCatalog> catalogs =
                coverageComponentCatalogRepository.findByCoverageTypeId(coverageTypeId);

        List<CoverageComponentResponseDto> result = new ArrayList<>();

        for (CoverageComponentCatalog ccc : catalogs) {

            CoverageComponent component =
                    coverageComponentRepository.findById(ccc.getCoverageComponentId())
                            .orElseThrow(() -> new RuntimeException("Component not found"));

            CoverageComponentResponseDto dto =
                    new CoverageComponentResponseDto(
                            component.getCoverageComponentId(),
                            component.getComponent()
                    );

            result.add(dto);
        }

        return result;
    }

    public TemplateResponseDto createTemplate(TemplateRequestDto request) {

        VehicleType vehicleType = vehicleTypeRepository.findById(request.getVehicleTypeId())
                .orElseThrow(() -> new RuntimeException("VehicleType not found"));

        Status status = statusRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Status not found"));

        Template template = new Template();
        template.setTemplateName(request.getTemplateName());
        template.setVehicleType(vehicleType);
        template.setStatus(status);

        Template saved = templateRepository.save(template);

        return TemplateResponseDto.builder()
                .templateId(saved.getTemplateId())
                .templateName(saved.getTemplateName())
                .vehicleType(saved.getVehicleType().getVehicle())
                .status(saved.getStatus().getStatus())
                .build();
    }

        
    public PremiumResponseDto calculatePremium(Integer templateId) {

        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        List<TemplatePlan> templatePlans =
                templatePlanRepository.findByTemplate(template);

        double liability = 0;
        double collision = 0;
        double comprehensive = 0;
        double addOn = 0;

        for (TemplatePlan tp : templatePlans) {

            List<TemplateCoverage> coverages =
                    templateCoverageRepository.findByTemplatePlan(tp);

            for (TemplateCoverage tc : coverages) {

                // 🔹 Get CoverageTypeCatalog
                CoverageTypeCatalog typeCatalog =
                        coverageTypeCatalogRepository.findById(tc.getCoverageTypeCatalogId())
                                .orElseThrow();

                // 🔹 Get Coverage
                Coverage coverage =
                        coverageRepository.findById(typeCatalog.getCoverageId())
                                .orElseThrow();

                // 🔥 PREMIUM LOGIC START

                if (coverage.getCoverage().equals("Liability")) {
                    liability += pricingService.calculateLiability(tc);
                }

                if (coverage.getCoverage().equals("Collision")) {
                    collision += pricingService.calculateCollision(tc.getCoverageTypeCatalogId());
                }

                if (coverage.getCoverage().equals("Comprehensive")) {
                    comprehensive += pricingService.calculateComprehensive(tc.getCoverageTypeCatalogId());
                }

                addOn += pricingService.calculateAddOns(
                        template.getVehicleType().getVehicleTypeId(),
                        typeCatalog.getCoverageId()
                );
            }
        }

        // 🔹 FINAL RESPONSE
        PremiumResponseDto premium = new PremiumResponseDto();

        premium.setLiabilityPremium(liability);
        premium.setCollisionPremium(collision);
        premium.setComprehensivePremium(comprehensive);
        premium.setAddOnPremium(addOn);
        premium.setTotalPremium(liability + collision + comprehensive + addOn);

        return premium;
    }


	
}
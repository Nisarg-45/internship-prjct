package com.onerivet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.dto.CoverageResponseDto;
import com.onerivet.dto.PlanResponseDto;
import com.onerivet.dto.PremiumResponseDto;
import com.onerivet.dto.TemplateFullResponseDto;
import com.onerivet.dto.TemplateRequestDto;
import com.onerivet.dto.TemplateResponseDto;
import com.onerivet.model.entity.Coverage;
import com.onerivet.model.entity.CoverageTypeCatalog;
import com.onerivet.model.entity.Status;
import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.TemplateCoverage;
import com.onerivet.model.entity.TemplatePlan;
import com.onerivet.model.entity.VehicleType;
import com.onerivet.repository.CoverageRepository;
import com.onerivet.repository.CoverageTypeCatalogRepository;
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

    public TemplateFullResponseDto getFullTemplate(Integer templateId) {

        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        List<TemplatePlan> templatePlans = templatePlanRepository.findByTemplate(template);

        List<PlanResponseDto> planDtos = new ArrayList<>();

        for (TemplatePlan tp : templatePlans) {

            List<TemplateCoverage> coverages =
                    templateCoverageRepository.findByTemplatePlan(tp);

            List<CoverageResponseDto> coverageDtos = new ArrayList<>();

            for (TemplateCoverage tc : coverages) {

                CoverageTypeCatalog typeCatalog =
                        coverageTypeCatalogRepository.findById(tc.getCoverageTypeCatalogId())
                                .orElseThrow();

                Coverage coverage =
                        coverageRepository.findById(typeCatalog.getCoverageId())
                                .orElseThrow();

                List<String> components =
                        componentService.getComponentNames(tc.getCoverageTypeCatalogId());

                List<String> addOns =
                        addOnService.getAddOnNames(
                                template.getVehicleType().getVehicleTypeId(),
                                typeCatalog.getCoverageId()
                        );

                CoverageResponseDto dto = CoverageResponseDto.builder()
                        .coverageTypeCatalogId(tc.getCoverageTypeCatalogId())
                        .coverageName(coverage.getCoverage())
                        .bodilyInjuredLimitPerPerson(tc.getBodilyInjuredLimitPerPerson())
                        .bodilyInjuredLimitPerAccident(tc.getBodilyInjuredLimitPerAccident())
                        .propertyDamageLimit(tc.getPropertyDamageLimit())
                        .combinedLimit(tc.getCombinedLimit())
                        .components(components)
                        .addOns(addOns)
                        .build();

                coverageDtos.add(dto);
            }

            PlanResponseDto planDto = PlanResponseDto.builder()
                    .planId(tp.getPlan().getPlanId())
                    .planName(tp.getPlan().getPlan())
                    .coverages(coverageDtos)
                    .build();

            planDtos.add(planDto);
        }

        return TemplateFullResponseDto.builder()
                .templateId(template.getTemplateId())
                .templateName(template.getTemplateName())
                .vehicleType(template.getVehicleType().getVehicle())
                .plans(planDtos)
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
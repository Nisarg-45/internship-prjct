package com.onerivet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.dto.PlanResponseDto;
import com.onerivet.model.entity.Plan;
import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.TemplatePlan;
import com.onerivet.model.entity.VehicleType;
import com.onerivet.repository.TemplatePlanRepository;
import com.onerivet.repository.TemplateRepopository;
import com.onerivet.repository.VehicleTypeRepository;

@Service
public class TemplatePlanService {

    @Autowired
    private TemplateRepopository templateRepository;

//    @Autowired
//    private PlanRepository planRepository;

    @Autowired
    private TemplatePlanRepository templatePlanRepository;
    
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

//    public void addPlansToTemplate(Integer templateId, List<Integer> planIds) {
//
//        Template template = templateRepository.findById(templateId)
//                .orElseThrow(() -> new RuntimeException("Template not found"));
//
//        for (Integer planId : planIds) {
//
//            Plan plan = planRepository.findById(planId)
//                    .orElseThrow(() -> new RuntimeException("Plan not found"));
//
//            TemplatePlan tp = new TemplatePlan();
//            tp.setTemplate(template);
//            tp.setPlan(plan);
//            tp.setCreatedDate(LocalDateTime.now());
//
//            templatePlanRepository.save(tp);
//        }
//    }
//    
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
                       // .coverages(null)
                        .build();

                result.add(dto);
            }
        }

        return result;
    }
}
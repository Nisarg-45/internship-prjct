package com.onerivet.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.onerivet.dto.PlanResponseDto;
import com.onerivet.model.entity.Plan;
import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.TemplatePlan;
import com.onerivet.model.entity.VehicleType;
import com.onerivet.repository.TemplatePlanRepository;
import com.onerivet.repository.TemplateRepository;
import com.onerivet.repository.VehicleTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final VehicleTypeRepository vehicleTypeRepository;
    private final TemplateRepository templateRepository;
    private final TemplatePlanRepository templatePlanRepository;

    public List<PlanResponseDto> getPlansByVehicleType(Integer vehicleTypeId) {

        VehicleType vehicleType = vehicleTypeRepository.findById(vehicleTypeId)
                .orElseThrow();

        List<Template> templates = templateRepository.findByVehicleType(vehicleType);

        Map<Integer, PlanResponseDto> map = new LinkedHashMap<>();

        for (Template t : templates) {
            for (TemplatePlan tp : templatePlanRepository.findByTemplate(t)) {

                Plan p = tp.getPlan();

                map.putIfAbsent(
                        p.getPlanId(),
                        PlanResponseDto.builder()
                                .planId(p.getPlanId())
                                .planName(p.getPlan())
                                .build()
                );
            }
        }

        return new ArrayList<>(map.values());
    }
}
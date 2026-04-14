package com.onerivet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.onerivet.dto.VehicleTypeResponseDto;
import com.onerivet.model.entity.VehicleType;
import com.onerivet.repository.VehicleTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    public List<VehicleTypeResponseDto> getVehicleTypes() {

        List<VehicleTypeResponseDto> result = new ArrayList<>();

        for (VehicleType v : vehicleTypeRepository.findAll()) {
            result.add(new VehicleTypeResponseDto(
                    v.getVehicleTypeId(),
                    v.getVehicle()
            ));
        }

        return result;
    }
}
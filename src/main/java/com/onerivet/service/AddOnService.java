package com.onerivet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.onerivet.dto.AddOnDto;
import com.onerivet.model.entity.AddOn;
import com.onerivet.repository.AddOnRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddOnService {

    private final AddOnRepository repository;

    public List<AddOnDto> getAddOns(Integer vehicleTypeId) {

        List<AddOn> list = repository.findByVehicleTypeId(vehicleTypeId);

        return list.stream().map(a -> {
            AddOnDto dto = new AddOnDto();

            dto.setAddOnId(a.getAddOnId());
            dto.setAddOn(a.getAddOn());
            dto.setPrice(a.getBasePrice());

            return dto;
        }).collect(Collectors.toList());
    }
}
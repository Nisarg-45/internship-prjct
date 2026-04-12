package com.onerivet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.model.entity.AddOn;
import com.onerivet.repository.AddOnRepository;

@Service
public class AddOnService {

    @Autowired
    private AddOnRepository addOnRepository;

    public List<String> getAddOnNames(Integer vehicleTypeId, Integer coverageId) {

        List<AddOn> addOns =
                addOnRepository.findByVehicleTypeIdAndCoverageId(vehicleTypeId, coverageId);

        return addOns.stream()
                .map(AddOn::getAddOn)
                .toList();
    }
}
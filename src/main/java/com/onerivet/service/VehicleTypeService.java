package com.onerivet.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.repository.VehicleTypeRepository;

@Service
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    public void initData() {

        vehicleTypeRepository.insertCar();
        vehicleTypeRepository.insertMotorcycle();
        vehicleTypeRepository.insertMotorcycle1();

        
    }
}
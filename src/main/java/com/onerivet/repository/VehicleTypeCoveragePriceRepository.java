package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onerivet.model.entity.VehicleTypeCoveragePrice;

@Repository
public interface VehicleTypeCoveragePriceRepository 
        extends JpaRepository<VehicleTypeCoveragePrice, Integer> {

    List<VehicleTypeCoveragePrice> findByVehicleTypeId(Integer vehicleTypeId);
}

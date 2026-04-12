package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.AddOn;

public interface AddOnRepository extends JpaRepository<AddOn, Integer> {

    List<AddOn> findByVehicleTypeIdAndCoverageId(Integer vehicleTypeId, Integer coverageId);

}
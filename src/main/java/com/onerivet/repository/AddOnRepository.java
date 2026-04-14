package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.AddOn;

public interface AddOnRepository extends JpaRepository<AddOn, Integer> {

    @Query("SELECT a FROM AddOn a WHERE a.vehicleType.vehicleTypeId = :vehicleTypeId")
    List<AddOn> findByVehicleTypeId(Integer vehicleTypeId);
}
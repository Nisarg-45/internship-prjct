package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.VehicleType;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {


	@Query("SELECT v FROM VehicleType v")
	List<VehicleType> findAllVehicleTypes();

}
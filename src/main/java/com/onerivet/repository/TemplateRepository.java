package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.VehicleType;

public interface TemplateRepository extends JpaRepository<Template, Integer>{
	List<Template> findByVehicleType(VehicleType vehicleType);
	
	@Query("SELECT t FROM Template t WHERE t.vehicleType.vehicleTypeId = :vehicleTypeId")
	List<Template> findByVehicleTypeId(Integer vehicleTypeId);
}
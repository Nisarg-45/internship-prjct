package com.onerivet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onerivet.model.entity.Template;
import com.onerivet.model.entity.VehicleType;

public interface TemplateRepopository extends JpaRepository<Template, Integer>{
	List<Template> findByVehicleType(VehicleType vehicleType);
}

package com.onerivet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.onerivet.model.entity.VehicleType;

import jakarta.transaction.Transactional;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO "Ref"."VehicleType" ("VehicleTypeId", "Vehicle", "CreatedDate")
        VALUES (1, 'Car', NOW())
        """, nativeQuery = true)
    void insertCar();

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO "Ref"."VehicleType" ("VehicleTypeId", "Vehicle", "CreatedDate")
        VALUES (2, 'Motorcycle', NOW())
        """, nativeQuery = true)
    void insertMotorcycle();
    
    
    @Modifying
    @Transactional
    @Query(value = """ 
    		
    		INSERT INTO "Ref"."VehicleType" ("VehicleTypeId", "Vehicle", "CreatedDate")
    		VALUES (3, 'Motorcycle', NOW())
    		""", nativeQuery = true)
    
    void insertMotorcycle1();
}
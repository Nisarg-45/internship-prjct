package com.onerivet.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "[VehicleType]", schema = "[Ref]")
@Getter
@Setter
public class VehicleType {

    @Id
    @Column(name = "[VehicleTypeId]", nullable = false)
    private Integer vehicleTypeId;

    @Column(name = "[Vehicle]", nullable = false)
    private String vehicle;

    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;

	public Integer getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Integer vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(LocalDateTime deletedDate) {
		this.deletedDate = deletedDate;
	}

	
}
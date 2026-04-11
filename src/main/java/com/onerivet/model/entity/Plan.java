package com.onerivet.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "[Plan]", schema = "[Ref]")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plan {

    @Id
    @Column(name = "[PlanId]", nullable = false)
    private Integer planId;

    @Column(name = "[Plan]", nullable = false)
    private String plan;

    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;
}
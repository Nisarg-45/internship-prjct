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
@Table(name = "[Coverage]", schema = "[Ref]")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coverage {

    @Id
    @Column(name = "[CoverageId]", nullable = false)
    private Integer coverageId;

    @Column(name = "[Coverage]", nullable = false)
    private String coverage;

    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;
}
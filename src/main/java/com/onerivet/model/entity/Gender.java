package com.onerivet.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[Gender]", schema = "[Ref]")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Gender {

    @Id
    @Column(name = "[GenderId]", nullable = false)
    private Integer genderId;

    @Column(name = "[Gender]", nullable = false)
    private String gender;

    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;

    // 🔗 ONE GENDER → MANY USERS
    @OneToMany(mappedBy = "Gender")
    private List<User> users;
}
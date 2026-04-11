package com.onerivet.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "[User]", schema = "[User]")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {

    @Id
    @Column(name = "[UserId]", nullable = false)
    private String userId;

    @Column(name = "[FirstName]", nullable = false)
    private String firstName;

    @Column(name = "[LastName]", nullable = false)
    private String lastName;

    @Column(name = "[DateOfBirth]", nullable = false)
    private Integer dateOfBirth;

    @Column(name = "[Email]", nullable = false)
    private String email;

    @Column(name = "[PhoneNumber]", nullable = false)
    private String phoneNumber;

    @Column(name = "[Address]", nullable = false)
    private String address;

    // 🔗 MANY USERS → ONE GENDER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "[GenderId]", nullable = false)
    private Gender Gender;

    // 🔗 MANY USERS → ONE ROLE
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "[RoleId]", nullable = false)
    private Role Role;

    @Column(name = "[CreatedById]")
    private String createdById;

    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[ModifiedById]")
    private String modifiedById;

    @Column(name = "[ModifiedDate]")
    private LocalDateTime modifiedDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;
}

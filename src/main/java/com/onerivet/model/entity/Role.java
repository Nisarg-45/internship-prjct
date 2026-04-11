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
@Table(name = "[Role]", schema = "[Ref]")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @Column(name = "[RoleId]", nullable = false)
    private String roleId;

    @Column(name = "[Role]", nullable = false)
    private String role;

    @Column(name = "[CreatedDate]", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "[DeletedDate]")
    private LocalDateTime deletedDate;

    // 🔗 ONE ROLE → MANY USERS
    @OneToMany(mappedBy = "Role")
    private List<User> users;
}
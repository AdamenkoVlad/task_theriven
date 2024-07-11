package com.example.task_theriven.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "Неправильний формат пошти")
    @Size(min = 2, max = 100)
    @Column(name = "email",unique = true)
    private String email;

    @Pattern(regexp = "\\+?\\d{6,14}",
            message = "Неправильний номер телефону")
    @Size(min = 6, max = 14)
    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "created", nullable = false, updatable = false)
    private Long created;

    @Column(name = "updated", nullable = false)
    private Long updated;

    @PrePersist
    protected void onCreate() {
        long currentTimeMillis = System.currentTimeMillis();
        created = currentTimeMillis;
        updated = currentTimeMillis;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = System.currentTimeMillis();
    }
}

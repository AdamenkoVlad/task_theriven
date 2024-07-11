package com.example.task_theriven.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$",
            message = "Неправильний формат пошти")
    @Size(min = 2, max = 100)
    @Column(name = "email", unique = true)
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
        this.created = currentTimeMillis;
        this.updated = currentTimeMillis;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = System.currentTimeMillis();
    }

    public String getFormattedCreated() {
        return formatDate(this.created);
    }

    public String getFormattedUpdated() {
        return formatDate(this.updated);
    }


    private String formatDate(Long timestamp) {
        LocalDate date = LocalDateTime.ofInstant(
                java.time.Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault()).toLocalDate();
        return date.toString();
    }
}

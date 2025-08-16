package com.birds.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Entity
@Table(name = "birds")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Bird {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Bird name is required")
    @Size(min = 1, max = 50, message = "Bird name must be between 1 and 50 characters")
    @Column(nullable = false, unique = true)
    private String name;
    
    @NotBlank(message = "Binomial name is required")
    @Size(min = 1, max = 50, message = "Binomial name must be between 1 and 50 characters")
    @Column(nullable = false, unique = true)
    private String binomialName;
    
    @Nullable
    @Size(max = 300, message = "Description cannot exceed 300 characters")
    @Column(length = 300)
    private String description;
    
    @NotNull(message = "Created date is required")
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Nullable
    @Column
    private LocalDateTime updatedAt;


    // Pre-persist method to set creation timestamp
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Pre-update method to set update timestamp
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

package com.birds.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "observations")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Observation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Observation date is required")
    @Column(nullable = false)
    private LocalDateTime observationDate;
    
    @NotNull(message = "Bird ID is required")
    @Column(nullable = false)
    private Long birdId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "birdId", insertable = false, updatable = false)
    @JsonIgnore
    private Bird bird;
    
    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90 degrees")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90 degrees")
    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;
    
    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180 degrees")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180 degrees")
    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;
    
    @Min(value = 1, message = "Count must be at least 1")
    @Column(nullable = false)
    private Integer count = 1;
    
    @Nullable
    @Size(max = 50, message = "Habitat type cannot exceed 50 characters")
    @Column(length = 50)
    private String habitatType;
    
    @NotNull(message = "Created date is required")
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Nullable
    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

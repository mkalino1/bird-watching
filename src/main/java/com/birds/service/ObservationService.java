package com.birds.service;

import com.birds.entity.Observation;
import com.birds.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ObservationService {
    
    @Autowired
    private ObservationRepository observationRepository;
    
    // Get all observations
    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }
    
    // Get observation by observation ID
    public Optional<Observation> getObservationById(@NonNull Long id) {
        return observationRepository.findById(id);
    }
    
    // Get observations by bird ID
    public List<Observation> getObservationsByBirdId(@NonNull Long birdId) {
        return observationRepository.findByBirdId(birdId);
    }
    
    
    // Get observations within a date range
    public List<Observation> getObservationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            return List.of();
        }
        return observationRepository.findByObservationDateBetween(startDate, endDate);
    }
    
    // Get observations within a geographic bounding box
    public List<Observation> getObservationsByLocationBounds(BigDecimal minLatitude, BigDecimal maxLatitude,
                                                            BigDecimal minLongitude, BigDecimal maxLongitude) {
        if (minLatitude == null || maxLatitude == null || minLongitude == null || maxLongitude == null) {
            return List.of();
        }
        return observationRepository.findByLocationBounds(minLatitude, maxLatitude, minLongitude, maxLongitude);
    }
    
    // Get all observations ordered by observation date (most recent first)
    public List<Observation> getAllObservationsOrderedByDate() {
        return observationRepository.findAllByOrderByObservationDateDesc();
    }
    
    // Get total count of observations
    public long getTotalObservationCount() {
        return observationRepository.count();
    }
    
    // Get count of observations for a specific bird
    public long getObservationCountByBird(@NonNull Long birdId) {
        return observationRepository.countByBirdId(birdId);
    }
    
    // Business logic: Get observations with optional filtering
    public List<Observation> getFilteredObservations(Long birdId, 
                                                    LocalDateTime startDate, 
                                                    LocalDateTime endDate,
                                                    BigDecimal minLatitude, 
                                                    BigDecimal maxLatitude,
                                                    BigDecimal minLongitude, 
                                                    BigDecimal maxLongitude,
                                                    boolean orderByDate) {
        
        // Business rule: Filter by bird ID takes precedence
        if (birdId != null) {
            return getObservationsByBirdId(birdId);
        }
        
        // Business rule: Date range filtering
        if (startDate != null && endDate != null) {
            return getObservationsByDateRange(startDate, endDate);
        }
        
        // Business rule: Location bounds filtering
        if (minLatitude != null && maxLatitude != null && minLongitude != null && maxLongitude != null) {
            return getObservationsByLocationBounds(minLatitude, maxLatitude, minLongitude, maxLongitude);
        }
        
        // Business rule: Default behavior - all observations, optionally ordered
        if (orderByDate) {
            return getAllObservationsOrderedByDate();
        }
        
        return getAllObservations();
    }
}

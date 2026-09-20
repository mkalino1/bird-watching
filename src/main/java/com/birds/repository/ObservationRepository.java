package com.birds.repository;

import com.birds.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {

    // Find observations by bird ID
    List<Observation> findByBirdId(Long birdId);
    
    
    // Find observations within a date range
    List<Observation> findByObservationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find observations by location (within a bounding box)
    @Query("SELECT o FROM Observation o WHERE o.latitude BETWEEN :minLat AND :maxLat AND o.longitude BETWEEN :minLon AND :maxLon")
    List<Observation> findByLocationBounds(@Param("minLat") java.math.BigDecimal minLatitude, 
                                         @Param("maxLat") java.math.BigDecimal maxLatitude,
                                         @Param("minLon") java.math.BigDecimal minLongitude, 
                                         @Param("maxLon") java.math.BigDecimal maxLongitude);
    
    // Find observations ordered by observation date (most recent first)
    List<Observation> findAllByOrderByObservationDateDesc();
    
    // Count observations by bird ID
    long countByBirdId(Long birdId);
}

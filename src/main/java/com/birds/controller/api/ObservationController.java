package com.birds.controller.api;

import com.birds.entity.Observation;
import com.birds.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    
    @Autowired
    private ObservationService observationService;
    
    // GET /api/observations - Get all observations with optional filtering
    @GetMapping
    public ResponseEntity<List<Observation>> getObservations(
            @RequestParam(required = false) Long birdId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) BigDecimal minLat,
            @RequestParam(required = false) BigDecimal maxLat,
            @RequestParam(required = false) BigDecimal minLon,
            @RequestParam(required = false) BigDecimal maxLon,
            @RequestParam(required = false, defaultValue = "false") boolean orderByDate) {
        
        // Delegate business logic to service layer
        List<Observation> observations = observationService.getFilteredObservations(
            birdId, startDate, endDate, minLat, maxLat, minLon, maxLon, orderByDate);
        
        return ResponseEntity.ok(observations);
    }

    // GET /api/observations/{id} - Get observation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Observation> getObservationById(@PathVariable @NonNull Long id) {
        Optional<Observation> observation = observationService.getObservationById(id);
        return observation.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }
    
    // GET /api/observations/count - Get total observation count
    @GetMapping("/count")
    public ResponseEntity<Long> getObservationCount(@RequestParam(required = false) Long birdId) {
        long count;
        if (birdId != null) {
            count = observationService.getObservationCountByBird(birdId);
        } else {
            count = observationService.getTotalObservationCount();
        }
        return ResponseEntity.ok(count);
    }
    
    // GET /api/observations/recent - Get recent observations (ordered by date, most recent first)
    @GetMapping("/recent")
    public ResponseEntity<List<Observation>> getRecentObservations() {
        List<Observation> observations = observationService.getAllObservationsOrderedByDate();
        return ResponseEntity.ok(observations);
    }
}

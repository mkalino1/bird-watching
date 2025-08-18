package com.birds.controller.api;

import com.birds.entity.Bird;
import com.birds.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/birds")
public class BirdController {
    
    @Autowired
    private BirdService birdService;
    
    // GET /api/birds - Get all birds or search by name
    @GetMapping
    public ResponseEntity<List<Bird>> getBirds(@RequestParam(required = false) String name) {
        List<Bird> birds = birdService.getBirdsOrSearchByName(name);
        return ResponseEntity.ok(birds);
    }

    // GET /api/birds/{id} - Get bird by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bird> getBirdById(@PathVariable @NonNull Long id) {
        Optional<Bird> bird = birdService.getBirdById(id);
        return bird.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    // GET /api/birds/count - Get total bird count
    @GetMapping("/count")
    public ResponseEntity<Long> getBirdCount() {
        long count = birdService.getTotalBirdCount();
        return ResponseEntity.ok(count);
    }
}

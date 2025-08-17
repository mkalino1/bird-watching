package com.birds.service;

import com.birds.entity.Bird;
import com.birds.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirdService {
    
    @Autowired
    private BirdRepository birdRepository;
    
    // Get all birds
    public List<Bird> getAllBirds() {
        return birdRepository.findAll();
    }
    
    // Get bird by ID
    public Optional<Bird> getBirdById(@NonNull Long id) {
        return birdRepository.findById(id);
    }

    // Search birds by name
    public List<Bird> searchBirdsByName(@NonNull String name) {
        return birdRepository.findByNameContaining(name);
    }
    
    // Get total count of birds
    public long getTotalBirdCount() {
        return birdRepository.count();
    }
}

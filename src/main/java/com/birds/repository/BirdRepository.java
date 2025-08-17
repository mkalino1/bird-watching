package com.birds.repository;

import com.birds.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Long> {

    List<Bird> findByNameContaining(String name);
}

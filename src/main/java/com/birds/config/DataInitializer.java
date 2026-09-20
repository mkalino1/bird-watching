package com.birds.config;

import com.birds.entity.Bird;
import com.birds.entity.Observation;
import com.birds.repository.BirdRepository;
import com.birds.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private BirdRepository birdRepository;
    
    @Autowired
    private ObservationRepository observationRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (birdRepository.count() == 0) {
            initializeSampleBirds();
        }
        if (observationRepository.count() == 0) {
            initializeSampleObservations();
        }
    }
    
    private void initializeSampleBirds() {
        Bird robin = new Bird()
            .setName("Robin")
            .setBinomialName("Erithacus rubecula")
            .setDescription("A small bird with orange-red breast, commonly seen in gardens and parks");
        birdRepository.save(robin);
        
        Bird greatTit = new Bird()
            .setName("Great Tit")
            .setBinomialName("Parus major")
            .setDescription("A colorful tit with yellow breast and black head, very common in urban areas");
        birdRepository.save(greatTit);
        
        Bird blackbird = new Bird()
            .setName("Blackbird")
            .setBinomialName("Turdus merula")
            .setDescription("A black bird with yellow beak, males are all black, females are brown");
        birdRepository.save(blackbird);
        
        Bird chaffinch = new Bird()
            .setName("Chaffinch")
            .setBinomialName("Fringilla coelebs")
            .setDescription("A colorful finch with pink breast, common in woodlands and gardens");
        birdRepository.save(chaffinch);
        
        Bird magpie = new Bird()
            .setName("Magpie")
            .setBinomialName("Pica pica")
            .setDescription("A black and white bird with long tail, very intelligent and adaptable");
        birdRepository.save(magpie);
        
        System.out.println("Bird data initialized!");
    }
    
    private void initializeSampleObservations() {
        Observation robinObs1 = new Observation()
            .setBirdId(1L)
            .setObservationDate(LocalDateTime.now().minusDays(5))
            .setLatitude(new BigDecimal("51.5074"))
            .setLongitude(new BigDecimal("-0.1278"))
            .setCount(2)
            .setHabitatType("Urban park");
        observationRepository.save(robinObs1);
        
        Observation robinObs2 = new Observation()
            .setBirdId(1L)
            .setObservationDate(LocalDateTime.now().minusDays(2))
            .setLatitude(new BigDecimal("51.5085"))
            .setLongitude(new BigDecimal("-0.1255"))
            .setCount(1)
            .setHabitatType("Garden");
        observationRepository.save(robinObs2);
        
        Observation greatTitObs = new Observation()
            .setBirdId(2L)
            .setObservationDate(LocalDateTime.now().minusDays(3))
            .setLatitude(new BigDecimal("51.5090"))
            .setLongitude(new BigDecimal("-0.1270"))
            .setCount(4)
            .setHabitatType("Residential area");
        observationRepository.save(greatTitObs);
        
        Observation blackbirdObs = new Observation()
            .setBirdId(3L)
            .setObservationDate(LocalDateTime.now().minusDays(1))
            .setLatitude(new BigDecimal("51.5100"))
            .setLongitude(new BigDecimal("-0.1290"))
            .setCount(1)
            .setHabitatType("Woodland edge");
        observationRepository.save(blackbirdObs);
        
        Observation chaffinchObs = new Observation()
            .setBirdId(4L)
            .setObservationDate(LocalDateTime.now().minusHours(6))
            .setLatitude(new BigDecimal("51.5110"))
            .setLongitude(new BigDecimal("-0.1250"))
            .setCount(3)
            .setHabitatType("Mixed woodland");
        observationRepository.save(chaffinchObs);
        
        Observation magpieObs = new Observation()
            .setBirdId(5L)
            .setObservationDate(LocalDateTime.now().minusHours(2))
            .setLatitude(new BigDecimal("51.5120"))
            .setLongitude(new BigDecimal("-0.1240"))
            .setCount(2)
            .setHabitatType("Urban area");
        observationRepository.save(magpieObs);
        
        System.out.println("Observation data initialized!");
    }
}

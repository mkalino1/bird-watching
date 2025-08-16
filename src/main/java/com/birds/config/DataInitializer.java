package com.birds.config;

import com.birds.entity.Bird;
import com.birds.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private BirdRepository birdRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (birdRepository.count() == 0) {
            initializeSampleBirds();
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
}

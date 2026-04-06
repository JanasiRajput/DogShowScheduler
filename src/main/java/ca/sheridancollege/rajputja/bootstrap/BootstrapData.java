package ca.sheridancollege.rajputja.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.rajputja.beans.Dog;
import ca.sheridancollege.rajputja.beans.Judge;
import ca.sheridancollege.rajputja.beans.Owner;
import ca.sheridancollege.rajputja.repositories.DogRepository;
import ca.sheridancollege.rajputja.repositories.JudgeRepository;
import ca.sheridancollege.rajputja.repositories.OwnerRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final OwnerRepository ownerRepo;
    private final DogRepository dogRepo;
    private final JudgeRepository judgeRepo;

    @Override
    public void run(String... args) throws Exception {

        // -------------------------
        // CREATE 6 OWNERS (ITALIAN NAMES)
        // -------------------------
        Owner o1 = Owner.builder().firstName("Giovanni").lastName("Rossi").build();
        Owner o2 = Owner.builder().firstName("Francesca").lastName("Bianchi").build();
        Owner o3 = Owner.builder().firstName("Lorenzo").lastName("Ferrari").build();
        Owner o4 = Owner.builder().firstName("Chiara").lastName("Romano").build();
        Owner o5 = Owner.builder().firstName("Marco").lastName("Esposito").build();
        Owner o6 = Owner.builder().firstName("Elena").lastName("Conti").build();

        ownerRepo.saveAll(Arrays.asList(o1, o2, o3, o4, o5, o6));

        // -------------------------
        // CREATE 3 JUDGES (ITALIAN NAMES)
        // -------------------------
        Judge j1 = Judge.builder().name("Judge Alessandro").build();
        Judge j2 = Judge.builder().name("Judge Martina").build();
        Judge j3 = Judge.builder().name("Judge Federico").build();

        judgeRepo.saveAll(Arrays.asList(j1, j2, j3));

        // -------------------------
        // CREATE 30 DOGS
        // -------------------------
        List<Dog> dogs = new ArrayList<>();

        for (int i = 1; i <= 30; i++) {
            Dog dog = Dog.builder()
                    .name("Cane_" + i) // "Cane" means dog in Italian
                    .owner(null)        // assign later
                    .judges(new ArrayList<>())
                    .build();
            dogs.add(dog);
        }

        dogRepo.saveAll(dogs);

        // -------------------------
        // ASSIGN OWNERS TO DOGS
        // -------------------------
        List<Owner> owners = ownerRepo.findAll();

        for (int i = 0; i < dogs.size(); i++) {
            Owner owner = owners.get(i % owners.size());
            Dog dog = dogs.get(i);

            dog.setOwner(owner);
            dogRepo.save(dog);
        }

        // -------------------------
        // ASSIGN JUDGES TO DOGS
        // -------------------------
        List<Judge> judges = judgeRepo.findAll();

        for (int i = 0; i < dogs.size(); i++) {
            Dog dog = dogs.get(i);

            // Assign 1 or 2 judges randomly
            dog.getJudges().add(judges.get(i % judges.size()));

            if (i % 2 == 0) {
                dog.getJudges().add(judges.get((i + 1) % judges.size()));
            }

            dogRepo.save(dog);
        }

        System.out.println(" Data Loaded Successfully with all the Names");
    }
}
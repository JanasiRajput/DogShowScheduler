package ca.sheridancollege.rajputja.controllers;


import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.sheridancollege.rajputja.beans.Dog;
import ca.sheridancollege.rajputja.beans.Judge;
import ca.sheridancollege.rajputja.beans.Owner;
import ca.sheridancollege.rajputja.repositories.DogRepository;
import ca.sheridancollege.rajputja.repositories.JudgeRepository;
import ca.sheridancollege.rajputja.repositories.OwnerRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/dogs")
public class DogController {

    private final DogRepository dogRepo;
    private final OwnerRepository ownerRepo;
    private final JudgeRepository judgeRepo;

    // -------------------------
    // LIST ALL DOGS
    // -------------------------
    @GetMapping
    public String listDogs(Model model) {
        model.addAttribute("dogs", dogRepo.findAll());
        return "dogs/listDogs";
    }

    // -------------------------
    // ADD DOG FORM
    // -------------------------
    @GetMapping("/add")
    public String showAddDogForm(Model model) {
        Dog dog = new Dog();
        dog.setJudges(new ArrayList<>());
        model.addAttribute("dog", dog);
        return "dogs/addDogs";
    }

    // -------------------------
    // SAVE DOG
    // -------------------------
    @PostMapping("/add")
    public String saveDog(@ModelAttribute Dog dog) {
        dogRepo.save(dog);
        return "redirect:/dogs";
    }

    // -------------------------
    // VIEW DOG DETAILS
    // -------------------------
    @GetMapping("/{id}")
    public String viewDog(@PathVariable Long id, Model model) {
        Dog dog = dogRepo.findById(id).orElse(null);
        model.addAttribute("dog", dog);
        return "dogs/dogDetails";
    }

    // -------------------------
    // ASSIGN OWNER TO DOG
    // -------------------------
    @GetMapping("/assignOwner")
    public String assignOwnerForm(Model model) {
        model.addAttribute("dogs", dogRepo.findAll());
        model.addAttribute("owners", ownerRepo.findAll());
        return "dogs/assignOwner";
    }

    @PostMapping("/assignOwner")
    public String assignOwner(Long dogId, Long ownerId) {

        Dog dog = dogRepo.findById(dogId).orElse(null);
        Owner owner = ownerRepo.findById(ownerId).orElse(null);

        dog.setOwner(owner);
        dogRepo.save(dog);

        return "redirect:/dogs/" + dogId;
    }

    // -------------------------
    // ASSIGN JUDGE TO DOG
    // -------------------------
    @GetMapping("/assignJudge")
    public String assignJudgeForm(Model model) {
        model.addAttribute("dogs", dogRepo.findAll());
        model.addAttribute("judges", judgeRepo.findAll());
        return "dogs/assignJudge";
    }

    @PostMapping("/assignJudge")
    public String assignJudge(Long dogId, Long judgeId) {

        Dog dog = dogRepo.findById(dogId).orElse(null);
        Judge judge = judgeRepo.findById(judgeId).orElse(null);

        dog.getJudges().add(judge);
        dogRepo.save(dog);

        return "redirect:/dogs/" + dogId;
    }

    // -------------------------
    // REMOVE JUDGE FROM DOG
    // -------------------------
    @GetMapping("/removeJudge")
    public String removeJudge(Long dogId, Long judgeId) {

        Dog dog = dogRepo.findById(dogId).orElse(null);
        Judge judge = judgeRepo.findById(judgeId).orElse(null);

        dog.getJudges().remove(judge);
        dogRepo.save(dog);

        return "redirect:/dogs/" + dogId;
    }
}
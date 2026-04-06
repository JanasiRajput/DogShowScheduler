package ca.sheridancollege.rajputja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.sheridancollege.rajputja.beans.Owner;
import ca.sheridancollege.rajputja.repositories.OwnerRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerRepository ownerRepo;

    // -------------------------
    // LIST ALL OWNERS
    // -------------------------
    @GetMapping
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerRepo.findAll());
        return "owners/listOwners";
    }

    // -------------------------
    // SHOW ADD OWNER FORM
    // -------------------------
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/addOwner";
    }

    // -------------------------
    // SAVE OWNER
    // -------------------------
    @PostMapping("/add")
    public String saveOwner(@ModelAttribute Owner owner) {
        ownerRepo.save(owner);
        return "redirect:/owners";
    }

    // -------------------------
    // VIEW OWNER DETAILS
    // -------------------------
    @GetMapping("/{id}")
    public String viewOwner(@PathVariable Long id, Model model) {
        Owner owner = ownerRepo.findById(id).orElse(null);
        model.addAttribute("owner", owner);
        return "owners/ownerDetails";
    }
}
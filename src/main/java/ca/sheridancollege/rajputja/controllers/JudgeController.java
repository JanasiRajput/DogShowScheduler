package ca.sheridancollege.rajputja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.sheridancollege.rajputja.beans.Judge;
import ca.sheridancollege.rajputja.repositories.JudgeRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/judges")
public class JudgeController {

    private final JudgeRepository judgeRepo;

    // -------------------------
    // LIST ALL JUDGES
    // -------------------------
    @GetMapping
    public String listJudges(Model model) {
        model.addAttribute("judges", judgeRepo.findAll());
        return "judges/listJudges";
    }

    // -------------------------
    // ADD JUDGE FORM
    // -------------------------
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("judge", new Judge());
        return "judges/addJudge";
    }

    // -------------------------
    // SAVE JUDGE
    // -------------------------
    @PostMapping("/add")
    public String saveJudge(@ModelAttribute Judge judge) {
        judgeRepo.save(judge);
        return "redirect:/judges";
    }

    // -------------------------
    // VIEW JUDGE DETAILS
    // -------------------------
    @GetMapping("/{id}")
    public String viewJudge(@PathVariable Long id, Model model) {
        Judge judge = judgeRepo.findById(id).orElse(null);
        model.addAttribute("judge", judge);
        return "judges/judgeDetails";
    }
}
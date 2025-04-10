package hh.backend.matchlist.web;

import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.backend.matchlist.domain.Competition;
import hh.backend.matchlist.domain.CompetitionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CompetitionController {

    @Autowired
    private CompetitionRepository competitionRepository;

    @GetMapping("/competitionlist")
    public String getCompetition(Model model) {
        model.addAttribute("competitions", competitionRepository.findAll());
        return "competitionlist";
    }
    
    @GetMapping("/addcompetition")
    public String addCompetition(Model model) {
        model.addAttribute("competition", new Competition());
        return "addcompetition";
    }

     @PostMapping("/addcompetition")
    public String addCategory(@ModelAttribute Competition competition) {
        competitionRepository.save(competition);
        return "redirect:/competitionlist";
    }

    
}

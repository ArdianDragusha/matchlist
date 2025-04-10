package hh.backend.matchlist.web;

import hh.backend.matchlist.domain.Competition;
import hh.backend.matchlist.domain.CompetitionRepository;
import hh.backend.matchlist.domain.Match;
import hh.backend.matchlist.domain.MatchRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @GetMapping("/matchlist")
    public String getMatchList(@RequestParam(required = false) String opponent,
            @RequestParam(required = false) String competition,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String sort,
            Model model) {
        List<Match> matches;

        boolean hasOpponent = opponent != null && !opponent.isEmpty();
        boolean hasCompetition = competition != null && !competition.isEmpty();
        boolean hasLocation = location != null && !location.isEmpty();

        if ("asc".equals(sort)) {
            if (hasOpponent && hasCompetition && hasLocation) {
                matches = matchRepository
                        .findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCaseAndLocationOrderByDateAsc(
                                opponent, competition, location);
            } else if (hasOpponent && hasLocation) {
                matches = matchRepository.findByOpponentContainingIgnoreCaseAndLocationOrderByDateAsc(opponent,
                        location);
            } else if (hasCompetition && hasLocation) {
                matches = matchRepository
                        .findByCompetition_NameContainingIgnoreCaseAndLocationOrderByDateAsc(competition, location);
            } else if (hasLocation) {
                matches = matchRepository.findByLocationOrderByDateAsc(location);
            } else if (hasOpponent && hasCompetition) {
                matches = matchRepository
                        .findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCaseOrderByDateAsc(
                                opponent, competition);
            } else if (hasOpponent) {
                matches = matchRepository.findByOpponentContainingIgnoreCaseOrderByDateAsc(opponent);
            } else if (hasCompetition) {
                matches = matchRepository.findByCompetition_NameContainingIgnoreCaseOrderByDateAsc(competition);
            } else {
                matches = matchRepository.findAllByOrderByDateAsc();
            }
        } else if ("desc".equals(sort)) {
            if (hasOpponent && hasCompetition && hasLocation) {
                matches = matchRepository
                        .findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCaseAndLocationOrderByDateDesc(
                                opponent, competition, location);
            } else if (hasOpponent && hasLocation) {
                matches = matchRepository.findByOpponentContainingIgnoreCaseAndLocationOrderByDateDesc(opponent,
                        location);
            } else if (hasCompetition && hasLocation) {
                matches = matchRepository
                        .findByCompetition_NameContainingIgnoreCaseAndLocationOrderByDateDesc(competition, location);
            } else if (hasLocation) {
                matches = matchRepository.findByLocationOrderByDateDesc(location);
            } else if (hasOpponent && hasCompetition) {
                matches = matchRepository
                        .findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCaseOrderByDateDesc(
                                opponent, competition);
            } else if (hasOpponent) {
                matches = matchRepository.findByOpponentContainingIgnoreCaseOrderByDateDesc(opponent);
            } else if (hasCompetition) {
                matches = matchRepository.findByCompetition_NameContainingIgnoreCaseOrderByDateDesc(competition);
            } else {
                matches = matchRepository.findAllByOrderByDateDesc();
            }
        } else {
            if (hasOpponent && hasCompetition && hasLocation) {
                matches = matchRepository
                        .findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCaseAndLocationOrderByDateAsc(
                                opponent, competition, location);
            } else if (hasOpponent && hasLocation) {
                matches = matchRepository.findByOpponentContainingIgnoreCaseAndLocationOrderByDateAsc(opponent,
                        location);
            } else if (hasCompetition && hasLocation) {
                matches = matchRepository
                        .findByCompetition_NameContainingIgnoreCaseAndLocationOrderByDateAsc(competition, location);
            } else if (hasLocation) {
                matches = matchRepository.findByLocationOrderByDateAsc(location);
            } else if (hasOpponent && hasCompetition) {
                matches = matchRepository.findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCase(
                        opponent, competition);
            } else if (hasOpponent) {
                matches = matchRepository.findByOpponentContainingIgnoreCase(opponent);
            } else if (hasCompetition) {
                matches = matchRepository.findByCompetition_NameContainingIgnoreCase(competition);
            } else {
                matches = StreamSupport.stream(matchRepository.findAll().spliterator(), false).toList();
            }
        }

        model.addAttribute("matches", matches);
        model.addAttribute("competitions", competitionRepository.findAll());
        return "matchlist";
    }

    @GetMapping("/addmatch")
    public String addMatchForm(Model model) {
        model.addAttribute("match", new Match());
        model.addAttribute("competitions", competitionRepository.findAll());
        return "addmatch";
    }

    @PostMapping("/addmatch")
    public String saveMatch(@ModelAttribute Match match) {
        matchRepository.save(match);
        return "redirect:/matchlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/matches/delete/{id}")
    public String deleteMatch(@PathVariable("id") Long id) {
        matchRepository.deleteById(id);

        return "redirect:/matchlist";
    }

    @RequestMapping(value = "/editmatch/{id}")
    public String editMatch(@PathVariable("id") Long id, Model model) {
        model.addAttribute("match", matchRepository.findById(id).get());
        model.addAttribute("competitions", competitionRepository.findAll());
        return "editmatch";
    }

    @PostMapping("/editmatch/{id}")
    public String updateMatch(@PathVariable("id") Long id, @ModelAttribute Match match) {
        match.setId(id);
        matchRepository.save(match);

        return "redirect:/matchlist";
    }

}
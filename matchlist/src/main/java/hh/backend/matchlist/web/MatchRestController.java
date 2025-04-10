package hh.backend.matchlist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import hh.backend.matchlist.domain.Match;
import hh.backend.matchlist.domain.MatchRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin
@Controller
public class MatchRestController {

    @Autowired
    private MatchRepository matchRepository;

    @RequestMapping(value="/matches", method=RequestMethod.GET)
    public @ResponseBody List<Match> matchListRest() {
        return (List<Match>) matchRepository.findAll();
    }

    @RequestMapping(value="/matches/{id}", method=RequestMethod.GET)
    public @ResponseBody Optional<Match> findMatchRest(@PathVariable("id") Long id) {
        return matchRepository.findById(id);
    }
    
    @RequestMapping(value="/matches", method=RequestMethod.POST)
    public @ResponseBody Match saveMatchRest(@RequestBody Match match) {
        return matchRepository.save(match);
    }
    }
    


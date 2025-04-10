package hh.backend.matchlist.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Match> matches;

    // Constructors
    public Competition() {
    }

    public Competition(Long id, String name, List<Match> matches) {
        this.id = id;
        this.name = name;
        this.matches = matches;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
package hh.backend.matchlist.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;
    private String opponent;
    private String location;
    private String result;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    public Match() {
    }

    public Match(Long id, String date, String opponent, String location, String result, Competition competition) {
        this.id = id;
        this.date = date;
        this.opponent = opponent;
        this.location = location;
        this.result = result;
        this.competition = competition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
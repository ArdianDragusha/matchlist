package hh.backend.matchlist.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findByOpponentContainingIgnoreCase(String opponent);

    List<Match> findByCompetition_NameContainingIgnoreCase(String name);

    List<Match> findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCase(String opponent, String name);

    List<Match> findAllByOrderByDateAsc();

    List<Match> findAllByOrderByDateDesc();

    List<Match> findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCaseOrderByDateAsc(String opponent,
            String competition);

    List<Match> findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCaseOrderByDateDesc(
            String opponent, String competition);

    List<Match> findByOpponentContainingIgnoreCaseOrderByDateAsc(String opponent);

    List<Match> findByOpponentContainingIgnoreCaseOrderByDateDesc(String opponent);

    List<Match> findByCompetition_NameContainingIgnoreCaseOrderByDateAsc(String competition);

    List<Match> findByCompetition_NameContainingIgnoreCaseOrderByDateDesc(String competition);

    List<Match> findByLocationOrderByDateAsc(String location);

    List<Match> findByLocationOrderByDateDesc(String location);

    List<Match> findByOpponentContainingIgnoreCaseAndLocationOrderByDateAsc(String opponent, String location);

    List<Match> findByOpponentContainingIgnoreCaseAndLocationOrderByDateDesc(String opponent, String location);

    List<Match> findByCompetition_NameContainingIgnoreCaseAndLocationOrderByDateAsc(String competition,
            String location);

    List<Match> findByCompetition_NameContainingIgnoreCaseAndLocationOrderByDateDesc(String competition,
            String location);

    List<Match> findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCaseAndLocationOrderByDateAsc(
            String opponent, String competition, String location);

    List<Match> findByOpponentContainingIgnoreCaseAndCompetition_NameContainingIgnoreCaseAndLocationOrderByDateDesc(
            String opponent, String competition, String location);
}

package hh.backend.matchlist;


import hh.backend.matchlist.domain.Match;
import hh.backend.matchlist.domain.MatchRepository;
import hh.backend.matchlist.domain.User;
import hh.backend.matchlist.domain.UserRepository;
import hh.backend.matchlist.domain.Competition;
import hh.backend.matchlist.domain.CompetitionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class MatchlistApplication {
	public static void main(String[] args) {
		SpringApplication.run(MatchlistApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MatchRepository matchRepo, CompetitionRepository compRepo, UserRepository userRepo) {
		return (args) -> {
			Competition bundesliga = new Competition(null, "Bundesliga", null);
			Competition ucl = new Competition(null, "Champions League", null);

			compRepo.save(bundesliga);
			compRepo.save(ucl);

			Match match1 = new Match(null, "12-09-2024", "Borussia Dortmund", "Home", "2-1", bundesliga);
			Match match2 = new Match(null, "01-10-2024", "Real Madrid", "Away", "1-3", ucl);

			matchRepo.save(match1);
			matchRepo.save(match2);

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User("user", passwordEncoder.encode("user"), "user@example.com", "USER");
        User admin = new User("admin", passwordEncoder.encode("admin"), "admin@example.com", "ADMIN");

        userRepo.save(user);
        userRepo.save(admin);
		};
	}
}
package io.andersfarr.football.Coach;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachService {

	@Autowired
	private CoachRepository coachRepository;
	
	public List<Coach> getAllCoaches(String name){
		List<Coach> coaches = new ArrayList<>();
		coachRepository.findByTeamName(name)
		.forEach(c-> coaches.add(c));
		return coaches;
	}
	
	public Coach getCoach(String name) {
		return coachRepository.findById(name).get();
	}
	
	public void addCoach(Coach coach) {
		coachRepository.save(coach);
	}
	
	public void updateCoach(Coach coach) {
		coachRepository.save(coach);
	}
	
	public void deleteCoach(String name) {
		coachRepository.deleteById(name);
	}
}

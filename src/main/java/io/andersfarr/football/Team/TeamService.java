package io.andersfarr.football.Team;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	public List<Team> getAllTeams(){
		List<Team> teams = new ArrayList<>();
		teamRepository.findAll()
		.forEach(t-> teams.add(t));
		return teams;
	}
	
	public Team getTeam(String name) {
		return teamRepository.findById(name).get();
	}
	
	public void addTeam(Team team) {
		teamRepository.save(team);
	}
	
	public void updateTeam(String name, Team team) {
		teamRepository.save(team);
	}
	
	public void deleteTeam(String name) {
		teamRepository.deleteById(name);
	}
}
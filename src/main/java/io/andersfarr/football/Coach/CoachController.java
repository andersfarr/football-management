package io.andersfarr.football.Coach;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.andersfarr.football.Team.Team;

@RestController
public class CoachController {
	
	@Autowired
	private CoachService coachService;
	
	@RequestMapping("/teams/{name}/coaches")
	public List<Coach> getAllCoaches(@PathVariable String name) {
		return coachService.getAllCoaches(name);
	}
	
	@RequestMapping("/teams/{teamId}/coaches/{coachName}")
	public Coach getCoach(@PathVariable String coachName) {
		return coachService.getCoach(coachName);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/teams/{teamName}/coaches")
	public void addCoach(@PathVariable String teamName, @RequestBody Coach coach) {
		coach.setTeam(new Team(teamName, ""));
		coachService.addCoach(coach);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/teams/{teamName}/coaches/{coachName}")
	public void updateCoach(@PathVariable String teamName, @PathVariable String coachName, @RequestBody Coach coach) {
		coach.setTeam(new Team(teamName, ""));
		coachService.updateCoach(coach);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/teams/{teamName}/coaches/{coachName}")
	public void deleteCoach(@PathVariable String coachName) {
		coachService.deleteCoach(coachName);
	}
}

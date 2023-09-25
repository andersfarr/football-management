package io.javabrains.Team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
	
	@Autowired
	private TeamService teamService;

	@RequestMapping("/teams")
	public List<Team> getAllTeams(){
		return teamService.getAllTeams();
	}
	
	@RequestMapping("/teams/{name}")
	public Team getTeam(@PathVariable String name) {
		return teamService.getTeam(name);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/teams")
	public void addTeam(@RequestBody Team team) {
		teamService.addTeam(team);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/teams/{name}")
	public void updateTopic(@PathVariable String name, @RequestBody Team team) {
		teamService.updateTeam(name, team);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/teams/{name}")
	public void deleteTopic(@PathVariable String name) {
		teamService.deleteTeam(name);
	}

}

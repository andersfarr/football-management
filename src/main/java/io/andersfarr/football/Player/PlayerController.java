package io.andersfarr.football.Player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.andersfarr.football.Team.Team;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping("/teams/{name}/players")
	public List<Player> getAllPlayeres(@PathVariable String name) {
		return playerService.getAllPlayers(name);
	}
	
	@RequestMapping("/teams/{teamId}/players/{playerName}")
	public Player getPlayer(@PathVariable String playerName) {
		return playerService.getPlayer(playerName);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/teams/{teamName}/players")
	public void addPlayer(@PathVariable String teamName, @RequestBody Player player) {
		player.setTeam(new Team(teamName, ""));
		playerService.addPlayer(player);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/teams/{teamName}/players/{playerName}")
	public void updatePlayer(@PathVariable String teamName, @PathVariable String playerName, @RequestBody Player player) {
		player.setTeam(new Team(teamName, ""));
		playerService.updatePlayer(player);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/teams/{teamName}/players/{playerName}")
	public void deletePlayer(@PathVariable String playerName) {
		playerService.deletePlayer(playerName);
	}

}

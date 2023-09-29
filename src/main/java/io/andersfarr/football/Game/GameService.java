package io.andersfarr.football.Game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.andersfarr.football.Player.Player;
import io.andersfarr.football.Player.PlayerService;
import io.andersfarr.football.Team.Team;
import io.andersfarr.football.Team.TeamService;

@Service
public class GameService {
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private PlayerService playerService;
	
	public String playGame(Game game) {
		Team homeTeam = teamService.getTeam(game.getHome());
		Team awayTeam = teamService.getTeam(game.getAway());
		
		List<Player> homePlayers = playerService.getAllPlayers(game.getHome());
		List<Player> awayPlayers = playerService.getAllPlayers(game.getAway());
		
		Player homePlayer = homePlayers.get((int)(Math.random()*homePlayers.size()));
		Player awayPlayer = awayPlayers.get((int)(Math.random()*awayPlayers.size()));
		String announcement = "It's a beautiful night in "+ homeTeam.getCity() + " and the " 
					+ game.getHome() + " are hosting the " + game.getAway() + ".\n";
		announcement += "We're looking forward to the matchup between " + homePlayer.getPosition() + " " + homePlayer.getName() 
					+ " and " + awayPlayer.getPosition() + " " + awayPlayer.getName() + ".\n";
		announcement += "Enjoy the game folks!";
		return announcement;
	}
}

package io.andersfarr.football.Player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.andersfarr.football.Coach.Coach;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	public List<Player> getAllPlayers(String name){
		ArrayList<Player> players = new ArrayList<>();
		playerRepository.findByTeamName(name)
		.forEach(p-> players.add(p));
		return players;
	}
	
	public Player getPlayer(String name) {
		return playerRepository.findById(name).get();
	}
	
	public void addPlayer(Player player) {
		playerRepository.save(player);
	}
	
	public void updatePlayer(Player player) {
		playerRepository.save(player);
	}
	
	public void deletePlayer(String name) {
		playerRepository.deleteById(name);
	}
}

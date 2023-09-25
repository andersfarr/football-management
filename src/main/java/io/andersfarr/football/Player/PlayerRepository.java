package io.andersfarr.football.Player;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, String>{

	public List<Player> findByTeamName(String name);

}

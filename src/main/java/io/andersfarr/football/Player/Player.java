package io.andersfarr.football.Player;

import io.andersfarr.football.Team.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {
	
	@Id
	private String name;
	private String position;
	
	@ManyToOne
	private Team team;
	
	public Player() {}
	
	public Player(String name, String position, Team team) {
		this.name = name;
		this.position = position;
		this.team = team;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}

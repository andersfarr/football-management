package io.andersfarr.football.Coach;

import io.andersfarr.football.Team.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Coach {
	@Id
	private String name;
	private String role;
	
	@ManyToOne
	private Team team;
	
	public Coach() {}
	
	public Coach(String name, String role, Team team) {
		this.name = name;
		this.role = role;
		this.team = team;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
}

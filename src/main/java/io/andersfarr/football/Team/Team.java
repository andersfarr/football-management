package io.andersfarr.football.Team;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Team {
	@Id
	private String name;
	private String city;
	//TODO: add more data fields (head coach, team captain, etc.)
	
	public Team() {}
	
	public Team(String name, String city) {
		this.name =name;
		this.setCity(city);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}

package io.andersfarr.football.Game;


public class Game {

	private String home;
	private String away;
	
	public Game() {
		
	}
	
	public Game(String home, String away) {
		this.home = home;
		this.away = away;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}
}

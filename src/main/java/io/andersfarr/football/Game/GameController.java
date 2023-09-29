package io.andersfarr.football.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping("/game")
	public String playGame(@RequestBody Game game) {
		return gameService.playGame(game);
	}
}

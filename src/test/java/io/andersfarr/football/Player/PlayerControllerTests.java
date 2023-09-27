package io.andersfarr.football.Player;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import io.andersfarr.football.FootballManagementApplication;
import io.andersfarr.football.Team.Team;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = FootballManagementApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class PlayerControllerTests {
	
	@Autowired
	private PlayerController playerController;
	
	@Autowired
    private MockMvc mvc;
	
	public void createTestPlayer(String name, String role) {
		playerController.addPlayer("test", new Player(name, role, new Team("test", "testCity")));
	}
	
	public void deleteTestPlayer(String name) {
		playerController.deletePlayer(name);
	}
	
	@Test
	public void givenPlayer_whenGetPlayer_thenStatus200() throws Exception {

	    createTestPlayer("testName", "QB");

	    mvc.perform(get("/teams/testCity/players")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$[0].name", is("testName")))
	      .andExpect(jsonPath("$[0].position", is("QB")));
	    
	    deleteTestPlayer("testName");
	}
	
	@Test
	public void givenPlayer_whenUpdatePlayer_thenStatus200() throws Exception {
		createTestPlayer("test", "QB");
		
		mvc.perform(put("teams/testCity/players/test")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"name\": \"test\",\"city\": \"testCity2\""))
			.andExpect(status().isOk());
		
		deleteTestPlayer("test");
	}
	
	@Test
	public void whenCreateCoach_thenStatus200() throws Exception{
		mvc.perform(post("teams/testCity/players")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"name\": \"testName\",\"role\": \"Head Coach\""))
			.andExpect(status().isOk());
		
		deleteTestPlayer("test");
	}
	
	@Test
	public void givenCoach_whenDeleteCoach_theStatus200() throws Exception{
		createTestPlayer("testName", "Head Coach");
		
		mvc.perform(delete("teams/testCity/coaches/testName")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	
}

package io.andersfarr.football.Team;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = FootballManagementApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class TeamControllerTests {
	
	@Autowired
	private TeamController teamController;
	
	@Autowired
    private MockMvc mvc;
	
	public void createTestTeam(String name, String city) {
		teamController.addTeam(new Team(name, city));
	}
	
	public void deleteTestTeam(String name) {
		teamController.deleteTeam(name);
	}
	
	@Test
	public void givenTeam_whenGetTeam_thenStatus200() throws Exception {

	    createTestTeam("test", "testCity");

	    mvc.perform(get("/teams")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$[0].name", is("test")))
	      .andExpect(jsonPath("$[0].city", is("testCity")));
	    
	    deleteTestTeam("test");
	}
	
	@Test
	public void givenTeam_whenUpdateTeam_thenStatus200() throws Exception {
		createTestTeam("test", "testCity");
		
		mvc.perform(put("teams/test")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"name\": \"test\",\"city\": \"testCity2\""))
			.andExpect(status().isOk());
		
		deleteTestTeam("test");
	}
	
	
	
	
	
	
}

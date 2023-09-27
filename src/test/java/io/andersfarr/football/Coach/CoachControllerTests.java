package io.andersfarr.football.Coach;

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
public class CoachControllerTests {
	
	@Autowired
	private CoachController coachController;
	
	@Autowired
    private MockMvc mvc;
	
	public void createTestCoach(String name, String role) {
		coachController.addCoach("test", new Coach(name, role, new Team("test", "testCity")));
	}
	
	public void deleteTestCoach(String name) {
		coachController.deleteCoach(name);
	}
	
	@Test
	public void givenCoach_whenGetCoach_thenStatus200() throws Exception {

	    createTestCoach("testName", "Head Coach");

	    mvc.perform(get("/teams/testCity/coaches")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(content()
	      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	      .andExpect(jsonPath("$[0].name", is("testName")))
	      .andExpect(jsonPath("$[0].role", is("Head Coach")));
	    
	    deleteTestCoach("testName");
	}
	
	@Test
	public void givenCoach_whenUpdateCoach_thenStatus200() throws Exception {
		createTestCoach("test", "testCity");
		
		mvc.perform(put("teams/testCity/coaches/test")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"name\": \"test\",\"city\": \"testCity2\""))
			.andExpect(status().isOk());
		
		deleteTestCoach("test");
	}
	
	@Test
	public void whenCreateCoach_thenStatus200() throws Exception{
		mvc.perform(post("teams/testCity/coaches")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"name\": \"testName\",\"role\": \"Head Coach\""))
			.andExpect(status().isOk());
		
		deleteTestCoach("test");
	}
	
	@Test
	public void givenCoach_whenDeleteCoach_theStatus200() throws Exception{
		createTestCoach("testName", "Head Coach");
		
		mvc.perform(delete("teams/testCity/coaches/testName")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	
}

package io.andersfarr.football.Coach;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CoachRepository extends CrudRepository<Coach, String>{
	
	public List<Coach> findByTeamName(String teamName);
}

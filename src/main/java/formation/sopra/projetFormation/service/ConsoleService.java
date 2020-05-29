package formation.sopra.projetFormation.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService implements CommandLineRunner {

	private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().basicAuthentication("user", "user");

	private static final String URL = "http://localhost:8080/web/rest/formation/";

	@Override
	public void run(String... args) throws Exception {
	}

	
}

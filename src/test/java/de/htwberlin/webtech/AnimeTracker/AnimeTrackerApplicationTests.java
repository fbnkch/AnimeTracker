package de.htwberlin.webtech.AnimeTracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnimeTrackerApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		// Prüft, ob der Spring Context startet
	}

	@Test
	void userApi_isReachable() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/animes", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void landingPage_isReachable() {
		ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}

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
		// Basic Auth Credentials für TestRestTemplate
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("demo", "demo123");
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/animes", HttpMethod.GET, entity, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void landingPage_isReachable() {
		// Basic Auth für die Startseite
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("demo", "demo123");
		HttpEntity<String> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange("/", HttpMethod.GET, entity, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}

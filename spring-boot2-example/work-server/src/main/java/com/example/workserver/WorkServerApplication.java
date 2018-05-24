package com.example.workserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
@RestController
public class WorkServerApplication {
	@GetMapping("/work")
	public String work() {
		RestTemplate restTemplate = new RestTemplate();
		int count = 0;
		for (int i = 0; i < 4; i++) {
			ResponseEntity<Void> entity = restTemplate
					.getForEntity(URI.create("http://localhost:8081/meet"), Void.class);
			if (entity.getStatusCode() == HttpStatus.OK) {
				count++;
			}
		}

		return "went to " + count + " meetings";
	}

	public static void main(String[] args) {
		SpringApplication.run(WorkServerApplication.class, args);
	}
}

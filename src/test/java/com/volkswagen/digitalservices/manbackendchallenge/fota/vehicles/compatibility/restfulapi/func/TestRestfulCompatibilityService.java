package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.RestfulCompatibilityService;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.conf.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.RestfulCompatibilityService.OK_BODY;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestRestfulCompatibilityService {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private RestfulCompatibilityService restfulCompatibilityServiceController;

	@Test
	public void contextLoads() {
		assertThat(restfulCompatibilityServiceController).isNotNull();
	}

	@Test
	public void statusIsOk() {
		assertThat(restTemplate.getForObject("http://localhost:" + port + Paths.VEHICLES + Paths.VEHICLES_STATUS, String.class)
				.contains(OK_BODY));
	}

}

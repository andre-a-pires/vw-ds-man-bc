package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.conf.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.restfulapi.func.CompatibilityService.OK_BODY;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestRestfulStatus {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CompatibilityService compatibilityServiceController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(compatibilityServiceController).isNotNull();
	}

	@Test
	public void statusIsOk() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + Paths.VEHICLES + Paths.VEHICLES_STATUS, String.class))
				.contains(OK_BODY);
	}

}

package com.volkswagen.digitalservices.manbackendchallenge;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func.DaemonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManBackendChallengeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManBackendChallengeApplication.class, args);
		new DaemonService().run();
	}

}

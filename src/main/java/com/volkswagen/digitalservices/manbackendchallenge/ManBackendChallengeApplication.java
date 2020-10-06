package com.volkswagen.digitalservices.manbackendchallenge;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func.DaemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ManBackendChallengeApplication {

	@Autowired
	DaemonService daemon;

	public static void main(String[] args) {
		SpringApplication.run(ManBackendChallengeApplication.class, args);
	}

	@Bean
	public ApplicationRunner startDaemon() {
		return args -> daemon.run();
	}

}

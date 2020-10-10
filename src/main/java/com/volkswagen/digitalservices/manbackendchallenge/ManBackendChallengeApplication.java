package com.volkswagen.digitalservices.manbackendchallenge;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func.DaemonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ManBackendChallengeApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ManBackendChallengeApplication.class, args);
		DaemonService daemon = context.getBean(DaemonService.class);
		daemon.run();
	}

}

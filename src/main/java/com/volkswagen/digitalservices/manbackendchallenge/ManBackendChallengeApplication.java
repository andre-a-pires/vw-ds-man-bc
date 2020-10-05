package com.volkswagen.digitalservices.manbackendchallenge;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf.DaemonConfiguration;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func.DaemonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ManBackendChallengeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ManBackendChallengeApplication.class, args);
		DaemonConfiguration config = context.getBean(DaemonConfiguration.class);
		new DaemonService(config).run();
	}

}

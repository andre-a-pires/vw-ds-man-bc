package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf.DaemonConfiguration;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.persistence.Feature;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.persistence.FeatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class DaemonService {
    static final Logger LOGGER = LoggerFactory.getLogger(DaemonService.class);

    private DaemonConfiguration config;

    @Autowired
    FeatureService featureService;

    // FIXME: DaemonConfiguration instance should be injected!!!
    public DaemonService(DaemonConfiguration config) {
        this.config = config;
    }

    public void run() {
        LOGGER.info("Daemon starting");
        LOGGER.info("Daemon folder-sweeping path: " + config.getFolder());

        while (true) {
            try {
                getCompletableFuture().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error("Daemon folder-sweeping interrupted", e);
            } catch (ExecutionException e) {
                LOGGER.error("Daemon folder-sweeping exception", e);
            }
        }
    }

    private CompletableFuture<Void> getCompletableFuture() {
        return CompletableFuture.runAsync(() -> {
            LOGGER.info("Folder-sweeping iteration start");

            // csv files to process
            if (false) {
                LOGGER.info("Processing soft and hard codes folder");
            } else {
                LOGGER.info("No soft or hard code files to process, thread taking a nap");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.error("error trying to postpone next sweeping iteration", e);
                }
            }
            LOGGER.info("Folder-sweeping iteration finished");
        });
    }
}

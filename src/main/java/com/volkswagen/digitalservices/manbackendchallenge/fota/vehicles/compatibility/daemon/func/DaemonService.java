package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf.DaemonConfiguration;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class DaemonService {
    static final Logger LOGGER = LoggerFactory.getLogger(DaemonService.class);

    @Autowired
    private DaemonConfiguration config;

    @Autowired
    private CodeService codeService;

    @Async
    public void run() {
        LOGGER.info("Daemon starting");
        LOGGER.info("Daemon folder-sweeping path: " + config.getFolder());

        while (true) {
            try {
                getCompletableFuture().get();
            } catch (InterruptedException e) {
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

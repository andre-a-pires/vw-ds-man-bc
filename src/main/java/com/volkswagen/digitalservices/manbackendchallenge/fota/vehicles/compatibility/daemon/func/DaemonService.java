package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf.DaemonConfiguration;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.persistence.FeatureEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public final class DaemonService {
    static final Logger LOGGER = LoggerFactory.getLogger(DaemonService.class);

    private DaemonConfiguration config;

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

    private static CompletableFuture<Void> getCompletableFuture() {
        return CompletableFuture.runAsync(() -> {
            LOGGER.info("Folder-sweeping iteration start");

            // test Hibernate
            EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("Feature");
            EntityManager entityManager = sessionFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist( new FeatureEntity( "Our very first feature!!!"));
            entityManager.persist( new FeatureEntity( "Our very second feature!!!"));
            entityManager.getTransaction().commit();
            entityManager.close();

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

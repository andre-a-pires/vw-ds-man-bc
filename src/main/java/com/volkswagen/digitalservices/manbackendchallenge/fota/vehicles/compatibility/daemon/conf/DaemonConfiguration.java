package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("daemon")
public class DaemonConfiguration {
    private String folder;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

}

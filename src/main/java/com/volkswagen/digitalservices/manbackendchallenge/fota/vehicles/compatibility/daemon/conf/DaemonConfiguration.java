package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("daemon")
public class DaemonConfiguration {
    private String input_dir;
    private String archive_dir;

    public String getInputDir() {
        return input_dir;
    }

    public String getProcessedDir() {
        return archive_dir;
    }

    public void setInputDir(String value) {
        this.input_dir = value;
    }

    public void setArchiveDir(String value) {
        this.archive_dir = value;
    }

}

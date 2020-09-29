package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class DaemonConfiguration {
    @Value("$(daemon.folder_sweeping_path)")
    private String path;

    public String getPath() {
        return path;
    }
}

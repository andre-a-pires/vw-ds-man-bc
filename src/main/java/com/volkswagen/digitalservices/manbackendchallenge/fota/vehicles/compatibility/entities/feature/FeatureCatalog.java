package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.feature;

public class FeatureCatalog {
    private Feature featureA;
    private Feature featureB;
    private Feature featureC;

    public Feature getFeatureA() {
        if(featureA == null) {
            featureA = new FeatureBuilder()
                    .addMandatorySoftwareCode(
                            "GdS6TI", "93ZSw9", "btZUSp", "MZgsou", "Di75Ry", "0vhcNa", "33MHDf", "Di75Ry", "L34Pur")
                    .addUnallowedSoftwareCode(
                            "ykzkfK", "87Zhwo", "y4XKWo", "ay0pW2", "44OmDi", "aJsd3i", "Qoflqf", "2EzZXE", "j3mmf8", "MUR8Lx", "E6GYk7", "rDJyQX")
                    .addMandatoryHardwareCode(
                            "rlTcbX", "wEEA00", "SoF5uL", "VhB9VY", "NWytcy")
                    .addUnallowedHardwareCode(
                            "yZDXJJ", "tMI8bI", "DS8tZU", "PgOtkv", "PuyTwj", "ObZw28", "ZCLFOe", "jyP5PK", "pS5ZQs", "rcjjPX", "6VO6Uq", "DAlCk4", "YxKjcX")
                    .build();
        }
        return featureA;
    }
    // TODO: features B and C

}

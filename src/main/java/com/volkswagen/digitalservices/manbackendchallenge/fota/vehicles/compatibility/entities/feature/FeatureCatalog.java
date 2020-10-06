package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.feature;

public class FeatureCatalog {
    private Feature a;
    private Feature b;
    private Feature c;

    public Feature getFeatureA() {
        if (a == null) {
            a = new FeatureBuilder()
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
        return a;
    }

    public Feature getFeatureB() {
        if (b == null) {
            b = new FeatureBuilder()
                    .addMandatorySoftwareCode(
                            "FhFXVE", "FVlp0N", "I25pUg", "PeQWGL", "LYZzKL", "Cd9t6T", "pYgxjp", "T55Adn", "cjKv9N")
                    .addUnallowedSoftwareCode(
                            "yfepdF", "Q54BVi", "1QNx4P", "u9XCsm", "RgrAEU", "0M97HZ", "BD80qR", "RGOkrt", "y4XKWo", "LV7Msr", "0OEvxe", "VLyf6R", "s1I5dm", "I4wRf9")
                    .addMandatoryHardwareCode(
                            "fMm4Hl", "PWO7oa", "F73iHn")
                    .addUnallowedHardwareCode(
                            "mrGqkV", "Ps19N7")
                    .build();
        }
        return b;
    }

    public Feature getFeatureC() {
        if (c == null) {
            c = new FeatureBuilder()
                    .addMandatorySoftwareCode(
                            "CtbvOZ", "bpdM7a", "rTM6gD", "LvXPPT", "Gwz57A", "NNTgVk", "dt5WJj", "zTUQwE", "ufX8mD", "0vhcNa", "UPCZFv", "uUZjNJ", "ljnm95", "QP8Bls", "egNmFq", "Gwz57A", "PJyE8c", "pYgxjp")
                    .addUnallowedSoftwareCode(
                            "T2WuvF")
                    .addMandatoryHardwareCode(
                            "wv2CZs", "CEuBzO", "Cu5fGc", "jvqI5i", "pZLSFn", "eUMxfa")
                    .addUnallowedHardwareCode(
                            "JY3Vcn", "8PielJ", "NcOLyY", "Zfahrb", "iKALCh", "6IHTbr")
                    .build();
        }
        return c;
    }

}

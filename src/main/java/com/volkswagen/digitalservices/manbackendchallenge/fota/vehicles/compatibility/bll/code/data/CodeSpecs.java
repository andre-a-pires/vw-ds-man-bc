package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data;

public final class CodeSpecs {
    // TODO: convert to enum
    public final class Type {
        public static final String SOFTWARE = "SOFTWARE";
        public static final String HARDWARE = "HARDWARE";
    }

    public enum Installation {
        MANDATORY,
        UNALLOWED;
    }

}

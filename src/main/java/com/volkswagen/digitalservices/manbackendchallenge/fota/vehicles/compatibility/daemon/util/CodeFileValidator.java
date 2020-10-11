package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.util;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeFileValidator {
    static final Logger LOGGER = LoggerFactory.getLogger(CodeFileValidator.class);

    public static boolean isValidLine(String[] line) {
        if (line.length != 2) {
            LOGGER.error("Invalid csv file line: has " + line.length + " fields");
            return false;
        }

        return true;
    }

    public static boolean isValidVin(String s) {
        // TODO: improve according to vin spec at wikipedia
        if (s == null) {
            LOGGER.warn("Null vin value");
            return false;
        } else if (s.length() != 17) {
            LOGGER.warn("Wrong size vin value: " + s);
            return false;
        }

        return true;
    }

    public static boolean isValidCode(String value) {
        // TODO: validate alpha-numeric chars without regex for performance purposes
        if(value == null || value.length() == 0) {
            LOGGER.warn("Null or empty code value");
            return false;
        }

        return value.matches("^[A-Za-z0-9]+$");
    }

}

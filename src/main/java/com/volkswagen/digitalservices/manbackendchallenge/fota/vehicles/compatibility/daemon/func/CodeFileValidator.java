package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func;

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

    public static boolean isValidCode(String value) {
        // TODO: validate alpha-numeric chars without regex for performance purposes
        return value != null && value.length() > 0 && value.matches("[A-za-z0-9]+");
    }

    public static boolean isValidVin(String s) throws InvalidCodeStructureException {
        // TODO: improve according to vin spec at wikipedia
        if (s == null) {
            LOGGER.error("Null vin value");
            return false;
        } else if (s.length() != 17) {
            LOGGER.error("Wrong size vin value: " + s);
            return false;
        }

        return true;
    }
}

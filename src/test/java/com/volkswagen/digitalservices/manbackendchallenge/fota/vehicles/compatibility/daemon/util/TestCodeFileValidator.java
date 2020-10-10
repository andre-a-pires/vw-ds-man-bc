package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.util;

import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.util.CodeFileValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCodeFileValidator {
    @Test
    public void testIsValidLine() {
        assertFalse(CodeFileValidator.isValidLine(new String[]{"some lonely value"}));
        assertFalse(CodeFileValidator.isValidLine(new String[]{"one", "two", "three"}));

        assertTrue(CodeFileValidator.isValidLine(new String[]{"some vin", "some code"}));
    }

    @Test
    public void testIsValidVin() throws InvalidCodeStructureException {
        assertFalse(CodeFileValidator.isValidVin(""));
        assertFalse(CodeFileValidator.isValidVin("my amazingly wrong vin value"));

        assertTrue(CodeFileValidator.isValidVin("WAUHFBFL1DN549274"));
    }

    @Test
    public void testIsValidCode() {
        assertFalse(CodeFileValidator.isValidCode(""));
        assertFalse(CodeFileValidator.isValidCode("$wrong_code!"));

        assertTrue(CodeFileValidator.isValidCode("abcde12345ABCDE"));
    }

}

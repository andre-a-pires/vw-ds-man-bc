package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func;

import com.opencsv.CSVReader;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf.DaemonConfiguration;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.Code;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.CodeService;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.HardwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.entities.code.SoftwareCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Controller
public class DaemonService {
    static final Logger LOGGER = LoggerFactory.getLogger(DaemonService.class);

    static final String SOFTWARE_CODE_FILE_NAME_PREFIX = "soft_";
    static final String HARDWARE_CODE_FILE_NAME_PREFIX = "hard_";

    @Autowired
    private DaemonConfiguration config;

    @Autowired
    private CodeService codeService;

    private String sweepingPath;

    @Async
    public void run() {
        LOGGER.info("Daemon starting");

        sweepingPath = System.getProperty("user.dir") + "/" + config.getFolder();

        LOGGER.info("Daemon folder-sweeping path: " + sweepingPath);

        while (true) {
            try {
                getCompletableFuture().get();
            } catch (InterruptedException e) {
                LOGGER.warn("Daemon folder-sweeping interrupted. App might have been shutdown");
            } catch (ExecutionException e) {
                LOGGER.error("Daemon folder-sweeping exception", e);
            }
        }
    }

    private CompletableFuture<Void> getCompletableFuture() {

        return CompletableFuture.runAsync(() -> {
            LOGGER.debug("Folder-sweeping iteration start");


            try (Stream<Path> paths = Files.walk(Paths.get(sweepingPath))) {
                paths.filter(isCodeFile)
                        .forEach(parseAndPersistCodeFile);
            } catch (IOException e) {
                LOGGER.error("Daemon folder walking failed", e);
            }

            // csv files to process
            if (false) {
                LOGGER.info("Processing soft and hard codes folder");
            } else {
                LOGGER.info("No code files to process, thread taking a nap");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.error("error trying to postpone next sweeping iteration", e);
                }
            }
            LOGGER.debug("Folder-sweeping iteration finished");
        });
    }

    private final Predicate<Path> isCodeFile = path -> {
        Path fileName = path.getFileName();

        return fileName.endsWith(".csv") &&
                ( fileName.getFileName().startsWith(SOFTWARE_CODE_FILE_NAME_PREFIX)
                || fileName.getFileName().startsWith(HARDWARE_CODE_FILE_NAME_PREFIX) );
    };

    private Consumer<Path> parseAndPersistCodeFile = path -> {
        try (CSVReader reader = new CSVReader(new FileReader(path.toFile()))) {
            reader.iterator().forEachRemaining(line -> {
                Code code = null;
                // FIXME: chained try blocks. look for try with default exception handling with straight syntax
                try {
                    code = createCodeInstance(path.getFileName(), reader.getLinesRead(), line);
                } catch (InvalidCodeStructureException e) {
                    LOGGER.error("Daemon csv file error parsing and persisting values");
                }
                codeService.persist(code);});
        } catch (FileNotFoundException e) {
            LOGGER.error("Daemon cvs file opening error");
        } catch (IOException e) {
            LOGGER.error("Daemon csv file handling error");
        }

    };

    private Code createCodeInstance(Path fileName, long currentLine, String[] line) throws InvalidCodeStructureException {
        if (! CodeFileValidator.isValidLine(line)) {
            LOGGER.error("Daemon processing aborting");
            throw new InvalidCodeStructureException();
        }

        String vin = line[0];
        String code = line[1];

        if(! CodeFileValidator.isValidVin(vin) || CodeFileValidator.isValidCode(code)) {
            throw new InvalidCodeStructureException();
        }

        if (fileName.startsWith(SOFTWARE_CODE_FILE_NAME_PREFIX)) {
            return new SoftwareCode(code);
        } else if (fileName.startsWith(HARDWARE_CODE_FILE_NAME_PREFIX)) {
            return new HardwareCode(code);
        } else {
            LOGGER.error("Daemon unknown code type found during processing");
            throw new InvalidCodeStructureException();
        }
    }

}

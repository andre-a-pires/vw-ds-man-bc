package com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.func;

import com.opencsv.CSVReader;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.HardwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.code.data.SoftwareCode;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.bll.vehicle.func.VehicleService;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.conf.DaemonConfiguration;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.InvalidCodeStructureException;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.data.VinCodePair;
import com.volkswagen.digitalservices.manbackendchallenge.fota.vehicles.compatibility.daemon.util.CodeFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class DaemonService {
    static final Logger LOGGER = LoggerFactory.getLogger(DaemonService.class);
    static final String SOFTWARE_CODE_FILE_NAME_PREFIX = "soft_";
    static final String HARDWARE_CODE_FILE_NAME_PREFIX = "hard_";

    @Autowired
    private DaemonConfiguration config;

    @Autowired
    private VehicleService vehicleService;

    private String sweepingPath;

    private ReentrantLock mutex = new ReentrantLock();

    @Async
    public void run() {
        LOGGER.info("Daemon starting");
        sweepingPath = config.getInputDir();
        LOGGER.info("Daemon folder-sweeping path: " + sweepingPath);



        while (true) {
            LOGGER.debug("Folder-sweeping iteration start");

            try (Stream<Path> paths = Files.walk(Paths.get(sweepingPath), 1)) {
                paths.filter(isCodeFile)
                        .forEach(handleCodeFile);
            } catch (IOException e) {
                LOGGER.error("Daemon folder walking failed", e);
            }

            LOGGER.debug("Folder-sweeping iteration finished");
        }
    }

    private final Predicate<Path> isCodeFile = path -> {
        String fileName = path.getFileName().toString();

        return ! Files.isDirectory(path) && fileName.endsWith(".csv") &&
                (fileName.startsWith(SOFTWARE_CODE_FILE_NAME_PREFIX)
                        || fileName.startsWith(HARDWARE_CODE_FILE_NAME_PREFIX));
    };

    private Consumer<Path> parseAndPersistCodeFile = path -> {
        try (CSVReader reader = new CSVReader(new FileReader(path.toFile()))) {
            reader.iterator().forEachRemaining(line -> {
                // FIXME: chained try blocks. look for try with default exception handling with straight syntax
                try {
                    VinCodePair vinCodePair = createVinCodePair(path.getFileName().toString(), line);
//                    LOGGER.info(vinCodePair.toString());
                    vehicleService.persistIfNew(vinCodePair);
                } catch (InvalidCodeStructureException e) {
                    LOGGER.error("Daemon csv file error parsing and persisting values");
                }
            });
        } catch (FileNotFoundException e) {
            LOGGER.error("Daemon cvs file opening error");
        } catch (IOException e) {
            LOGGER.error("Daemon csv file handling error");
        }

    };

    private VinCodePair createVinCodePair(String fileName, String[] line) throws InvalidCodeStructureException {
        if (!CodeFileValidator.isValidLine(line)) {
            LOGGER.error("Daemon processing aborting");
            throw new InvalidCodeStructureException();
        }

        String vinValue = line[0];
        String codeValue = line[1];

        if (!CodeFileValidator.isValidVin(vinValue) || !CodeFileValidator.isValidCode(codeValue)) {
            throw new InvalidCodeStructureException();
        }

        if (fileName.startsWith(SOFTWARE_CODE_FILE_NAME_PREFIX)) {
            return new VinCodePair(vinValue, new SoftwareCode(codeValue));
        } else if (fileName.startsWith(HARDWARE_CODE_FILE_NAME_PREFIX)) {
            return new VinCodePair(vinValue, new HardwareCode(codeValue));
        } else {
            LOGGER.error("Daemon unknown code type found during processing");
            throw new InvalidCodeStructureException();
        }
    }

    private Consumer<Path> moveProcessedCodeFile = sourcePath -> {
        Path archivePath = Path.of(config.getArchiveDir());

        try {
            Files.move(sourcePath, archivePath.resolve(sourcePath.getFileName()));
        } catch (IOException e) {
            LOGGER.error("Daemon could not move processed code file from " + sourcePath.toString() + " to " + archivePath, e);
        }
    };

    private Consumer<Path> handleCodeFile = path -> {
        LOGGER.info("Started handling file " + path.getFileName().toString());

        mutex.lock();
        parseAndPersistCodeFile.accept(path);
        moveProcessedCodeFile.accept(path);
        mutex.unlock();

        LOGGER.info("Finished handling file " + path.getFileName().toString());
    };

}

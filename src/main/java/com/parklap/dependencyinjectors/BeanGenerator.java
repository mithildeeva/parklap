package com.parklap.dependencyinjectors;

import com.parklap.ParkApplication;
import com.parklap.entities.commands.CommandFactory;
import com.parklap.entities.parking.Lot;
import com.parklap.repositories.InMemParkingRepository;
import com.parklap.repositories.ParkingRepository;
import com.parklap.services.ParkingService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Central place for all beans to be
 * generated with their dependencies injected.
 * Can be extended to extend any functionality.
 */
public class BeanGenerator {

    private static BeanGenerator beanGenerator;

    private BeanGenerator() {}

    /**
     * Singleton pattern assuming -
     * The application will be run on a single thread
     * @return BeanGenerator
     */
    public static BeanGenerator getInstance() {
        if (null == beanGenerator) { beanGenerator = new BeanGenerator(); }
        return beanGenerator;
    }

    public ParkApplication getParkApp() {
        return new ParkApplication(getParkingService(), getBufferedReader(), getBufferedWriter());
    }

    public ParkingService getParkingService() { return new ParkingService(getCommandFactory(), getParkingRepository()); }

    private ParkingRepository getParkingRepository() { return new InMemParkingRepository(getParkingLot()); }

    private Lot getParkingLot() { return new Lot(); }

    private CommandFactory getCommandFactory() { return new CommandFactory(); }

    /**
     * Can be overridden to change the source of input
     * @return BufferedReader
     */
    private BufferedReader getBufferedReader() { return new BufferedReader(new InputStreamReader(System.in)); }

    /**
     * Can be overridden to change the destination of output
     * @return BufferedWriter
     */
    private BufferedWriter getBufferedWriter() { return new BufferedWriter(new OutputStreamWriter(System.out)); }
}

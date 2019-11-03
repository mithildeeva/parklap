package com.parklap;

import com.parklap.dependencyinjectors.BeanGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * To test only the end-to-end functionality (sanity check)
 */
public class E2ETest {
    private ParkApplication app;
    private BufferedWriter bWriter;

    @Before public void bootTest() throws IOException {
        app = BeanGenerator.getInstance().getParkApp();
        bWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    @Test public void createParkingFor6() throws IOException {
        testCommand("create_parking_lot 6", "Created a parking lot with 6 slots");
    }

    @Test public void parkACarInParkingFor1() throws IOException {
        app.processCommand("create_parking_lot 1");
        testCommand("park KA-01-HH-1234 White", "Allocated slot number: 1");
    }

    @Test public void leaveParking() throws IOException {
        app.processCommand("create_parking_lot 2");
        app.processCommand("park KA-01-HH-1334 White");
        app.processCommand("park KA-01-BB-0001 Black");

        testCommand("leave 2", "Slot number 2 is free");
    }

    @Test public void tryParkInFullParking() throws IOException {
        app.processCommand("create_parking_lot 2");
        app.processCommand("park KA-01-HH-1334 White");
        app.processCommand("park KA-01-BB-0001 Black");
        testCommand("park KA-01-BB-0231 Purple", "ERROR: ParkingFull- Sorry, parking lot is full");
    }

    @Test public void getParkingLotStatus() throws IOException {
        app.processCommand("create_parking_lot 2");
        app.processCommand("park KA-01-HH-1334 White");
        app.processCommand("park KA-01-BB-0001 Black");
        testCommand("status", "Slot No.\tRegistration No.\tColor\n1\tKA-01-HH-1334\tWHITE\n2\tKA-01-BB-0001\tBLACK\n");
    }

    @Test public void getRegNoForWhiteCars() throws IOException {
        app.processCommand("create_parking_lot 2");
        app.processCommand("park KA-01-HH-1334 White");
        app.processCommand("park KA-01-BB-0001 White");
        testCommand("registration_numbers_for_cars_with_colour White", "KA-01-HH-1334, KA-01-BB-0001");
    }

    @Test public void getSlotIdForWhiteCars() throws IOException {
        app.processCommand("create_parking_lot 2");
        app.processCommand("park KA-01-HH-1334 White");
        app.processCommand("park KA-01-BB-0001 White");
        testCommand("slot_numbers_for_cars_with_colour White", "1, 2");
    }

    @Test public void getSlotIdForCarsWithRegNo() throws IOException {
        app.processCommand("create_parking_lot 2");
        app.processCommand("park KA-01-HH-1334 White");
        app.processCommand("park KA-01-BB-0001 White");
        testCommand("slot_number_for_registration_number KA-01-BB-0001", "2");
    }

    private void testCommand(String command, String expectedOp) throws IOException {
        writeOut(String.format("INPUT: %s%n", command));
        String op = app.processCommand(command);

        Assert.assertEquals(expectedOp, op);
        writeOut(String.format("OUTPUT: %s%n", op));
    }

    private void writeOut(String msg) throws IOException {
        bWriter.write(msg );
        bWriter.flush();
    }
}

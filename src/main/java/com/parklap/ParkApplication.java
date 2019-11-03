package com.parklap;

import com.parklap.constants.Constants;
import com.parklap.entities.InputCommand;
import com.parklap.entities.CommandResult;
import com.parklap.services.ParkingService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ParkApplication {

    private ParkingService service;
    private BufferedReader bReader;
    private BufferedWriter bWriter;

    public ParkApplication(ParkingService service, BufferedReader bReader, BufferedWriter bWriter) {
        this.service = service;
        this.bReader = bReader;
        this.bWriter = bWriter;
    }

    void run() throws IOException {
        writeOut("Please initialize the parking lot.\nHint: 'create_parking_lot 6'\nEnter 'exit' to exit");

        while (true) {
            String output = processCommand(bReader.readLine());
            writeOut(output);

            if (Constants.EXIT_OUTPUT.getValue().equals(output)) break;
        }
    }

    /**
     * @param inputString raw input
     * @return output of the command
     * @throws IOException
     */
    public String processCommand(String inputString) throws IOException {
        InputCommand input = new InputCommand(inputString);
        // to exit the application
        if (Constants.EXIT_COMMAND.getValue().equals(input.getName())) {
            return (new CommandResult(Constants.EXIT_OUTPUT.getValue())).toString();
        }
        CommandResult result;
        try {
            result = service.executeCommand(input);
        } catch (Exception e) {
            result = new CommandResult(String.format("ERROR: %s- %s", e.getClass().getSimpleName(), e.getMessage()));
        }
        return result.toString();
    }

    private void writeOut(String msg) throws IOException {
        bWriter.write(msg + "\n");
        bWriter.flush();
    }
}

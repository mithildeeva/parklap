package com.parklap.entities.commands;

import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import com.parklap.entities.vehicles.Car;
import com.parklap.exceptions.InvalidCommand;
import com.parklap.repositories.ParkingRepository;

public class ParkCar implements Command {
    public static final String COMMAND_NAME = "park";

    private String carRegNo;
    private String carColor;

    @Override
    public CommandResult execute(InputCommand input, ParkingRepository repo) throws Exception {
        setInputParams(input);
        int slotId = repo.parkCar(new Car(carRegNo, carColor));
        return new CommandResult(String.format("Allocated slot number: %s", slotId));
    }

    /**
     * Cast inputs from string to correct types
     *
     * @param input
     * @throws InvalidCommand
     */
    private void setInputParams(InputCommand input) throws InvalidCommand {
        if (input.getParams().length < 2) throw new InvalidCommand("Please enter car registration no. followed by color");
        try {
            carRegNo = input.getParams()[0].toUpperCase();
            carColor = input.getParams()[1].toUpperCase();
        } catch (NumberFormatException e) {
            throw new InvalidCommand("Something went wrong.\nMake sure the command is of the format: 'park KA-01-HH-9999 White'");
        }
    }
}

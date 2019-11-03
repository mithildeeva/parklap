package com.parklap.entities.commands;

import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import com.parklap.exceptions.InvalidCommand;
import com.parklap.repositories.ParkingRepository;

public class CreateParkingLot implements Command {
    public static final String COMMAND_NAME = "create_parking_lot";
    private int countOfSlots;

    @Override
    public CommandResult execute(InputCommand input, ParkingRepository repo) throws InvalidCommand {
        setInputParams(input);
        repo.createParking(countOfSlots);
        return new CommandResult(String.format("Created a parking lot with %s slots", countOfSlots));
    }

    private void setInputParams(InputCommand input) throws InvalidCommand {
        if (input.getParams().length < 1) throw new InvalidCommand("Please enter the number of slots for the parking lot");
        try {
            countOfSlots = Integer.parseInt(input.getParams()[0]);
        } catch (NumberFormatException e) {
            throw new InvalidCommand("Expected input param should be an integer.\nMake sure command is of the format: 'create_parking_lot 6'");
        }
    }
}

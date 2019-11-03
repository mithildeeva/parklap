package com.parklap.entities.commands;

import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import com.parklap.entities.parking.Slot;
import com.parklap.exceptions.InvalidCommand;
import com.parklap.repositories.ParkingRepository;

public class GetCarNumbersForColor implements Command {
    public static final String COMMAND_NAME = "registration_numbers_for_cars_with_colour";

    private String color;

    @Override
    public CommandResult execute(InputCommand input, ParkingRepository repo) throws Exception {
        setInputParams(input);
        String[] carNumbers = repo.getCarNumbersForColor(color);
        if (carNumbers.length == 0) return new CommandResult("Not Found");
        return new CommandResult(String.join(", ", carNumbers));
    }

    private void setInputParams(InputCommand input) throws InvalidCommand {
        if (input.getParams().length < 1) throw new InvalidCommand("Please enter a color too");
        try {
            color = input.getParams()[0].toUpperCase();
        } catch (NumberFormatException e) {
            throw new InvalidCommand("Something went wrong.\nMake sure the command is of the format: 'registration_numbers_for_cars_with_colour White'");
        }
    }
}

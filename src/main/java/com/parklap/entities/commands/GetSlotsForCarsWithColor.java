package com.parklap.entities.commands;

import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import com.parklap.exceptions.InvalidCommand;
import com.parklap.repositories.ParkingRepository;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GetSlotsForCarsWithColor implements Command {
    public static final String COMMAND_NAME = "slot_numbers_for_cars_with_colour";

    private String color;

    @Override
    public CommandResult execute(InputCommand input, ParkingRepository repo) throws Exception {
        setInputParams(input);
        int[] slotIds = repo.getSlotsForCarsWithColor(color);
        if (slotIds.length == 0) return new CommandResult("Not Found");
        return new CommandResult(Arrays.stream(slotIds)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ")));
    }

    private void setInputParams(InputCommand input) throws InvalidCommand {
        if (input.getParams().length < 1) throw new InvalidCommand("Please enter a color too");
        try {
            color = input.getParams()[0].toUpperCase();
        } catch (NumberFormatException e) {
            throw new InvalidCommand("Something went wrong.\nMake sure the command is of the format: 'slot_numbers_for_cars_with_colour White'");
        }
    }
}

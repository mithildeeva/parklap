package com.parklap.entities.commands;

import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import com.parklap.exceptions.InvalidCommand;
import com.parklap.repositories.ParkingRepository;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GetSlotsForCarsWithNumber implements Command {
    public static final String COMMAND_NAME = "slot_number_for_registration_number";

    String carNumber;

    @Override
    public CommandResult execute(InputCommand input, ParkingRepository repo) throws Exception {
        setInputParams(input);
        int[] slotIds = repo.getSlotsForCarsWithNumber(carNumber);
        if (slotIds.length == 0) return new CommandResult("Not Found");
        return new CommandResult(Arrays.stream(slotIds)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ")));
    }

    private void setInputParams(InputCommand input) throws InvalidCommand {
        if (input.getParams().length < 1) throw new InvalidCommand("Please enter the car's registration number too");
        try {
            carNumber = input.getParams()[0].toUpperCase();
        } catch (NumberFormatException e) {
            throw new InvalidCommand("Something went wrong.\nMake sure the command is of the format: 'slot_number_for_registration_number MH-04-AY-1111'");
        }
    }
}

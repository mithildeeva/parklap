package com.parklap.entities.commands;

import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import com.parklap.exceptions.InvalidCommand;
import com.parklap.repositories.ParkingRepository;

public class FreeSlot implements Command {
    public static final String COMMAND_NAME = "leave";

    private int slotId;

    @Override
    public CommandResult execute(InputCommand input, ParkingRepository repo) throws Exception {
        setInputParams(input);
        repo.freeSlot(slotId);
        return new CommandResult(String.format("Slot number %s is free", slotId));
    }

    private void setInputParams(InputCommand input) throws InvalidCommand {
        if (input.getParams().length < 1) throw new InvalidCommand("Please enter the slot number the car is parked in");
        try {
            slotId = Integer.parseInt(input.getParams()[0]);
        } catch (NumberFormatException e) {
            throw new InvalidCommand("Expected input param should be an integer.\nMake sure command is of the format: 'leave 4'");
        }
    }
}

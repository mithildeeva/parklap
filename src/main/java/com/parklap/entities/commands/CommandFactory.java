package com.parklap.entities.commands;

import com.parklap.entities.InputCommand;
import com.parklap.exceptions.InvalidCommand;


public class CommandFactory {

    public Command getCommand(InputCommand inputCommand) throws InvalidCommand {
        switch (inputCommand.getName()) {
            case CreateParkingLot.COMMAND_NAME:
                return new CreateParkingLot();
            case FreeSlot.COMMAND_NAME:
                return new FreeSlot();
            case ParkCar.COMMAND_NAME:
                return new ParkCar();
            case GetCarNumbersForColor.COMMAND_NAME:
                return new GetCarNumbersForColor();
            case GetParkingLotStatus.COMMAND_NAME:
                return new GetParkingLotStatus();
            case GetSlotsForCarsWithColor.COMMAND_NAME:
                return new GetSlotsForCarsWithColor();
            case GetSlotsForCarsWithNumber.COMMAND_NAME:
                return new GetSlotsForCarsWithNumber();
            default:
                throw new InvalidCommand("Invalid Command!");
        }
    }
}

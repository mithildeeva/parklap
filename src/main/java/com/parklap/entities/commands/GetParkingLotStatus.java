package com.parklap.entities.commands;

import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import com.parklap.entities.parking.Slot;
import com.parklap.repositories.ParkingRepository;

public class GetParkingLotStatus implements Command {
    public static final String COMMAND_NAME = "status";

    @Override
    public CommandResult execute(InputCommand input, ParkingRepository repo) throws Exception {
        Slot[] slots = repo.getLotStatus();
        String message = getFormattedLotStatus(slots);
        return new CommandResult(message);
    }

    private String getFormattedLotStatus(Slot[] slots) {
        StringBuilder sb = new StringBuilder("Slot No.\tRegistration No.\tColor\n");
        for (Slot slot: slots) {
            sb.append(slot.getSlotId())
                    .append("\t")
                    .append(slot.getCar().getNumber())
                    .append("\t")
                    .append(slot.getCar().getColor())
                    .append("\n");
        }
        return sb.toString();
    }
}

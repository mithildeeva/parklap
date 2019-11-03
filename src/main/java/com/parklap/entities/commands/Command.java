package com.parklap.entities.commands;

import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import com.parklap.repositories.ParkingRepository;

public interface Command {

    CommandResult execute(InputCommand input, ParkingRepository repo) throws Exception;
}

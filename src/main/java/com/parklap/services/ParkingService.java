package com.parklap.services;

import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import com.parklap.entities.commands.Command;
import com.parklap.entities.commands.CommandFactory;
import com.parklap.exceptions.InvalidCommand;
import com.parklap.repositories.ParkingRepository;

public class ParkingService {

    private CommandFactory commandFactory;
    private ParkingRepository repo;

    public ParkingService(CommandFactory commandFactory, ParkingRepository repo) {
        this.commandFactory = commandFactory;
        this.repo = repo;
    }

    /**
     * Fetches the command from CommandFactory, executes and returns the output
     *
     * @param input
     * @return CommandResult
     * @throws Exception
     */
    public CommandResult executeCommand(InputCommand input) throws Exception {

        Command command = commandFactory.getCommand(input);

        return command.execute(input, repo);
    }
}

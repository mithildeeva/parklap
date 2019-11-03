package com.parklap.services;

import com.parklap.dependencyinjectors.BeanGenerator;
import com.parklap.entities.CommandResult;
import com.parklap.entities.InputCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingServiceTest {
    ParkingService service;
    @Before public void init() {
        service = BeanGenerator.getInstance().getParkingService();
    }

    @Test public void testExecuteCommandOutput() throws Exception {
        CommandResult result = service.executeCommand(new InputCommand("create_parking_lot 6"));
        Assert.assertEquals("Created a parking lot with 6 slots", result.toString());
    }
}

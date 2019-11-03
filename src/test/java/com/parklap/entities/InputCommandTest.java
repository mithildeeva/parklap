package com.parklap.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InputCommandTest {
    private InputCommand inputCommand;
    @Before public void init() {
        inputCommand = new InputCommand("input_CommAnd parAm1 param2");
    }

    @Test public void assertCommandName() {
        Assert.assertEquals("input_command", inputCommand.getName());
    }

    @Test public void assertParamArrayLength() {
        Assert.assertEquals(2, inputCommand.getParams().length);
    }

    @Test public void assertParamName() {
        Assert.assertEquals("parAm1", inputCommand.getParams()[0]);
    }
}

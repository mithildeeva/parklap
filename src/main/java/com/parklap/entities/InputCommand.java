package com.parklap.entities;

import java.util.Arrays;

/**
 * Sanirized version of the command string from input
 */
public class InputCommand {

    private String name;
    private String[] params;

    public InputCommand(String commandWithParams) {
        /*
        * 0th index of commandAndParams will contain the command
        * and rest members will be params
        * */
        String[] commandAndParams = commandWithParams.trim().split("\\s+");
        if (commandAndParams.length == 0) {
            return;
        }

        setName(commandAndParams[0]);
        if (commandAndParams.length > 1) {
            setParams(Arrays.copyOfRange(commandAndParams, 1, commandAndParams.length));
        } else {
            setParams(new String[0]);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }
}

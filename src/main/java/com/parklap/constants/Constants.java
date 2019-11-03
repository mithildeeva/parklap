package com.parklap.constants;

public enum Constants {
    EXIT_COMMAND("exit"),
    EXIT_OUTPUT("exiting..");

    private final String value;
    Constants(String value) { this.value = value; }

    public String getValue() { return value; }
}

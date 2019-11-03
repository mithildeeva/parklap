package com.parklap.entities;

public class CommandResult {
    private String message;

    public CommandResult(String msg) {
        setMessage(msg);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

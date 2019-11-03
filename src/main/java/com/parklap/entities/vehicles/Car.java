package com.parklap.entities.vehicles;

public class Car {
    private String number;
    private String color;

    public Car(String number, String color) {
        setColor(color);
        setNumber(number);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number.toUpperCase();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color.toUpperCase();
    }
}

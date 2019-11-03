package com.parklap.entities.parking;

import com.parklap.entities.vehicles.Car;

/**
 * A parking slot in a parking lot.
 */
public class Slot {
    private int slotId;
    private Car car;

    public Slot(int slotId) {
        this.slotId = slotId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getSlotId() {
        return slotId;
    }
}

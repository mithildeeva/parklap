package com.parklap.repositories;

import com.parklap.entities.parking.Lot;
import com.parklap.entities.parking.Slot;
import com.parklap.entities.vehicles.Car;
import com.parklap.exceptions.ParkingFull;
import com.parklap.exceptions.ParkingLotNotInitialized;

/**
 * This Repository uses an in-memory parking lot for persisting data.
 */
public class InMemParkingRepository implements ParkingRepository {
    private Lot lot;

    public InMemParkingRepository(Lot lot) { this.lot = lot; }


    @Override
    public void createParking(int slots) { lot.setSlots(slots); }

    @Override
    public int parkCar(Car car) throws ParkingLotNotInitialized, ParkingFull { return lot.parkCar(car); }

    @Override
    public void freeSlot(int slotId) throws ParkingLotNotInitialized { lot.freeSlot(slotId); }

    @Override
    public Slot[] getLotStatus() throws ParkingLotNotInitialized {return lot.getStatus(); }

    @Override
    public String[] getCarNumbersForColor(String color) throws ParkingLotNotInitialized { return lot.getCarNumbersForColor(color); }

    @Override
    public int[] getSlotsForCarsWithColor(String color) throws ParkingLotNotInitialized { return lot.getSlotsForCarsWithColor(color); }

    @Override
    public int[] getSlotsForCarsWithNumber(String number) throws ParkingLotNotInitialized { return lot.getSlotsForCarsWithNumber(number); }
}

package com.parklap.repositories;

import com.parklap.entities.parking.Slot;
import com.parklap.entities.vehicles.Car;
import com.parklap.exceptions.ParkingFull;
import com.parklap.exceptions.ParkingLotNotInitialized;

public interface ParkingRepository {

    void createParking(int slots);
    int parkCar(Car car) throws ParkingLotNotInitialized, ParkingFull;
    void freeSlot(int slotId) throws ParkingLotNotInitialized;
    Slot[] getLotStatus() throws ParkingLotNotInitialized;
    String[] getCarNumbersForColor(String color) throws ParkingLotNotInitialized;
    int[] getSlotsForCarsWithColor(String color) throws ParkingLotNotInitialized;
    int[] getSlotsForCarsWithNumber(String number) throws ParkingLotNotInitialized;
}

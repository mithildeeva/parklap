package com.parklap.entities.parking;

import com.parklap.entities.vehicles.Car;
import com.parklap.exceptions.ParkingFull;
import com.parklap.exceptions.ParkingLotNotInitialized;
import com.parklap.util.Utility;

import java.util.*;

/**
 * In-memory parking lot
 */
public class Lot {
    private boolean lotInitialized;
    private Map<Integer, Slot> occupiedSlots;
    private SortedSet<Slot> freeSlots;
    private int totalSlots;

    /**
     * Initializes the parking lot.
     * @param count of slots in the current parking lot
     */
    public void setSlots(int count) {
        this.occupiedSlots = new HashMap<>();
        this.totalSlots = count;

        setSortedSlots();
        for (int i = 1; i <= count; i++) {
            freeSlots.add(new Slot(i));
        }
        lotInitialized = true;
    }

    public int parkCar(Car car) throws ParkingLotNotInitialized, ParkingFull {
        validateParkingLot();

        if (!hasEmptySlot()) throw new ParkingFull("Sorry, parking lot is full");

        Slot slot = popFirstFreeSlot();
        slot.setCar(car);
        occupySlot(slot);
        return slot.getSlotId();
    }

    public void freeSlot(int slotId) throws ParkingLotNotInitialized {
        validateParkingLot();

        Slot slot = occupiedSlots.remove(slotId);
        if (null == slot) return;

        slot.setCar(null);
        freeSlots.add(slot);
    }

    public Slot[] getStatus() throws ParkingLotNotInitialized {
        validateParkingLot();

        List<Slot> slots = new ArrayList<>(occupiedSlots.values());
        slots.sort(Comparator.comparingInt(Slot::getSlotId));
        return slots.toArray(new Slot[0]);
    }

    public String[] getCarNumbersForColor(String color) throws ParkingLotNotInitialized {
        validateParkingLot();

        List<String> carNumbers = new ArrayList<>();
        occupiedSlots.forEach((k, v) -> {
            if (v.getCar().getColor().equals(color)) carNumbers.add(v.getCar().getNumber());
        });
        return carNumbers.toArray(new String[0]);
    }

    public int[] getSlotsForCarsWithColor(String color) throws ParkingLotNotInitialized {
        validateParkingLot();

        List<Integer> slots = new ArrayList<>();
        occupiedSlots.forEach((k, v) -> {
            if (v.getCar().getColor().equals(color)) slots.add(v.getSlotId());
        });
        return Utility.integerListToInt(slots);
    }

    public int[] getSlotsForCarsWithNumber(String number) throws ParkingLotNotInitialized {
        validateParkingLot();

        List<Integer> slots = new ArrayList<>();
        occupiedSlots.forEach((k, v) -> {
            if (v.getCar().getNumber().equals(number)) slots.add(v.getSlotId());
        });
        return Utility.integerListToInt(slots);
    }

    private Slot popFirstFreeSlot() {
        Slot slot = freeSlots.first();
        freeSlots.remove(slot);
        return slot;
    }

    private void occupySlot(Slot slot) {
        occupiedSlots.put(slot.getSlotId(), slot);
    }

    private boolean hasEmptySlot() {
        return occupiedSlots.size() != totalSlots;
    }

    private void setSortedSlots() {
        freeSlots = new TreeSet<>(Comparator.comparingInt(Slot::getSlotId));
    }

    private void validateParkingLot() throws ParkingLotNotInitialized {
        if (! lotInitialized) throw new ParkingLotNotInitialized("Please create a parking lot first. Hint: 'create_parking_lot 6'");
    }
}

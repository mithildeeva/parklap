package com.parklap.entities.parking;

import com.parklap.entities.vehicles.Car;
import com.parklap.exceptions.ParkingFull;
import com.parklap.exceptions.ParkingLotNotInitialized;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LotTest {
    private Lot lot;

    @Before public void init() {
        lot = new Lot();
        lot.setSlots(2);
    }

    @Test public void assertNumberOfCarNumbersForBlackCar() throws ParkingLotNotInitialized, ParkingFull {
        lot.parkCar(new Car("1", "BLACK"));
        Assert.assertEquals(1, lot.getCarNumbersForColor("BLACK").length );
    }

    @Test public void freeASlot() throws ParkingLotNotInitialized, ParkingFull {
        lot.parkCar(new Car("2", "GREY"));
        lot.parkCar(new Car("1", "BLACK"));
        lot.freeSlot(2);
        // count of occupied slot
        Assert.assertEquals(1, lot.getStatus().length );
    }

    @Test public void assertNumberOfSlotsForWhiteCars() throws ParkingLotNotInitialized, ParkingFull {
        lot.parkCar(new Car("2", "WHITE"));
        lot.parkCar(new Car("1", "WHITE"));
        Assert.assertEquals(2, lot.getSlotsForCarsWithColor("WHITE").length );
    }

    @Test public void assertSlotIdForCarWithNumber() throws ParkingLotNotInitialized, ParkingFull {
        lot.parkCar(new Car("2", "BLACK"));
        lot.parkCar(new Car("1", "WHITE"));
        Assert.assertEquals(2, lot.getSlotsForCarsWithNumber("1")[0] );
    }
}

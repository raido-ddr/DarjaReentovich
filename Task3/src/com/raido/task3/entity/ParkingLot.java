package com.raido.task3.entity;

import com.raido.task3.exception.ResourceException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ParkingLot {

    private final BlockingQueue<ParkingSpace> parkingSpaces;

    public ParkingLot(int capacity) {

        parkingSpaces = new ArrayBlockingQueue<ParkingSpace>(capacity);
        for(int i = 1; i <= capacity; i++) {
            parkingSpaces.add(new ParkingSpace(i));
        }
    }

    public ParkingSpace occupySpace(int timeout)
            throws ResourceException, InterruptedException {
        return parkingSpaces.poll(timeout, TimeUnit.MILLISECONDS);

    }

    public void releaseSpace(ParkingSpace space) {
        parkingSpaces.add(space);
    }


}

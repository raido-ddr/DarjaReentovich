package com.raido.task3.entity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingSpace {

    private int id;

    private Lock lock;

    public ParkingSpace(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public void occupy(int stayDuration) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(stayDuration);
    }
}

package com.raido.task3.entity;

import com.raido.task3.exception.ResourceException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Car extends Thread {

    private String model;

    private int stayDuration;

    private int timeout;

    private ParkingLot parkingLot;

    private ParkingSpace parkingSpace;

    private static final Logger LOGGER;

    private Exchanger<ParkingSpace> exchanger;

    static {
        LOGGER = Logger.getLogger(Car.class);
        new DOMConfigurator().doConfigure("log4j.xml",
                LogManager.getLoggerRepository());
        LOGGER.setLevel(Level.INFO);
    }

    public Car(String model, int stayDuration, int timeout, ParkingLot parkingLot,
        Exchanger<ParkingSpace> exchanger) {
        this.model = model;
        this.stayDuration = stayDuration;
        this.timeout = timeout;
        this.parkingLot = parkingLot;
        this.exchanger = exchanger;
    }

    public String getModel() {
        return model;
    }

    public void reportArrival() {
        LOGGER.info(model + " arrives");
    }

    public void reportLeaving() {
        LOGGER.info(model + " leaves");
    }

    private void reportGivingUp() {
        LOGGER.info(model + " gives up waiting");
    }

    private void reportParking(int spaceId) {
        LOGGER.info(model + " parks at space " + spaceId);
    }

    @Override
    public void run() {

        try {
            reportArrival();
            parkingSpace = parkingLot.occupySpace(timeout);

            if(parkingSpace != null) {
                reportParking(parkingSpace.getId());
                exchangeParkingSpace(parkingSpace);
                parkingSpace.occupy(stayDuration);
            } else {
                reportGivingUp();
            }

        } catch (ResourceException e) {
            LOGGER.error(e);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        } finally {
            if(parkingSpace != null) {
                parkingLot.releaseSpace(parkingSpace);
                reportLeaving();
            }
        }

    }


    private void exchangeParkingSpace(ParkingSpace parkingSpace) {
        try {
            int exchangeTimeout = randomTimeout();
            ParkingSpace newSpace =
                    exchanger.exchange(parkingSpace, exchangeTimeout, TimeUnit.MILLISECONDS);

            //Timeout wasn't exceeded
            if(newSpace != null) {
                if(isAdjacentSpace(newSpace.getId())) {
                    parkingSpace = newSpace;
                    reportSpaceExchange(parkingSpace.getId());
                } else {
                    //Space was not adjacent
                    LOGGER.info(model +
                            " failed to exchanged space for " + newSpace.getId());
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        } catch (TimeoutException e) {
            /*LOGGER.info(model + ": space exchange timeout exceeded for "
                    + parkingSpace.getId());*/
            LOGGER.error(e);
        }

    }

    private int randomTimeout() {
        return (new Random().nextInt(1000) + 500);
    }

    private void reportSpaceExchange(int id) {
        LOGGER.info(model + " exchanged space for " + id);
    }

    public boolean isAdjacentSpace(int spaceId) {
        return (Math.abs(this.parkingSpace.getId() - spaceId) == 1);
    }
}

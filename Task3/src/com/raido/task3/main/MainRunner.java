package com.raido.task3.main;

import com.raido.task3.entity.Car;
import com.raido.task3.entity.ParkingLot;
import com.raido.task3.entity.ParkingSpace;

import java.util.LinkedList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainRunner {

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(5);

        LinkedList<Car> carList = new LinkedList<>();
        Exchanger<ParkingSpace> exchanger = new Exchanger<>();

        carList.add(new Car("Land Rover Defender", 5000, 3000, parkingLot, exchanger));
        carList.add(new Car("Porsche Cayenne", 6000, 5000, parkingLot, exchanger));
        carList.add(new Car("BMW X5", 5000, 5000, parkingLot, exchanger));
        carList.add(new Car("VW Golf", 5000, 6200, parkingLot, exchanger));
        carList.add(new Car("Volvo XC90", 6000, 2000, parkingLot, exchanger));
        carList.add(new Car("Dodge Challenger", 7000, 3000, parkingLot, exchanger));
        carList.add(new Car("Renault Laguna", 6000, 5500, parkingLot, exchanger));
        carList.add(new Car("Chevrolet Tahoe", 5000, 2500, parkingLot, exchanger));
        carList.add(new Car("Range Rover", 4900, 3000, parkingLot, exchanger));
        carList.add(new Car("VW Touareg", 5700, 4800, parkingLot, exchanger));
        carList.add(new Car("Dodge Ram", 8000, 4500, parkingLot, exchanger));
        carList.add(new Car("BMW i8", 5700, 6400, parkingLot, exchanger));
        carList.add(new Car("Peugeot 806", 5600, 6000, parkingLot, exchanger));
        carList.add(new Car("Ford Mondeo", 6800, 3400, parkingLot, exchanger));
        carList.add(new Car("Renault Logan", 6100, 4800, parkingLot, exchanger));
        carList.add(new Car("Jeep Cherokee", 5600, 3000, parkingLot, exchanger));
        carList.add(new Car("Audi A6", 5200, 7000, parkingLot, exchanger));
        carList.add(new Car("Volvo S60", 6000, 2200, parkingLot, exchanger));
        carList.add(new Car("Honda Civic", 4000, 4100, parkingLot, exchanger));
        carList.add(new Car("Porsche Panamera", 5500, 4200, parkingLot, exchanger));
        carList.add(new Car("Audi Q5", 4000, 4700, parkingLot, exchanger));

        //The less this value is, the more cars would be able to exchange spaces
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(7);

        for(int i = 0; i < carList.size(); i++) {
            executor.schedule(carList.get(i), 10 * i, TimeUnit.MILLISECONDS);
        }

        executor.shutdown();

    }

}

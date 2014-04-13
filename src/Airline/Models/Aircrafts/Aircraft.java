package Airline.Models.Aircrafts;

abstract public class Aircraft {

    public String name;
    public String model;
    public int passengerCapacity;
    public int crewCapacity;

    //constructor
    public Aircraft(
            String name,
            String model,
            int numberOfPassengers,
            int crewCapacity) {

        this.name = name;
        this.model = model;
        this.passengerCapacity = numberOfPassengers;
        this.crewCapacity = crewCapacity;
    }
}

package Airline.Models.Aircrafts;

abstract public class Aircraft {

    public String name;
    public String model;
    public int passengerCapacity;
    public int numberOfCrew;

    //constructor
    public Aircraft(
            String name,
            String model,
            int numberOfPassengers,
            int numberOfCrew) {

        this.name = name;
        this.model = model;
        this.passengerCapacity = numberOfPassengers;
        this.numberOfCrew = numberOfCrew;
    }
}

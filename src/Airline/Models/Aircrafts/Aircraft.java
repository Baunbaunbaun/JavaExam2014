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
            int passengerCapacity,
            int crewCapacity) {

        this.name = name;
        this.model = model;
        this.passengerCapacity = passengerCapacity;
        this.crewCapacity = crewCapacity;
    }
    @Override
    public String toString(){
        return ("Name: " + this.name + "Model: " + this.model + "Passenger seats: " + this.passengerCapacity + "Number of crew: " + this.crewCapacity);
    }
}

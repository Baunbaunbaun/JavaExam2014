package Airline.Model.Aircraft;

import java.io.Serializable;

public class Aircraft implements Serializable {

    public final String name;
    public final String model;
    public final int passengerCapacity;
    public final int crewCapacity;

    public Aircraft(String name, String model, int passengerCapacity, int crewCapacity) {
        this.name = name;
        this.model = model;
        this.passengerCapacity = passengerCapacity;
        this.crewCapacity = crewCapacity;
    }

    @Override
    public String toString() {
        return ("Name: " + this.name + "Model: " + this.model + "Passenger seats: " + this.passengerCapacity +
                "Number of crew: " + this.crewCapacity);
    }
}

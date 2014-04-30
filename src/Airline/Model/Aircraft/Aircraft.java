package Airline.Model.Aircraft;

import java.io.Serializable;

public class Aircraft implements Serializable {

    public final String model;
    public final int passengerCapacity;
    public final int crewCapacity;

    public Aircraft(String model, int passengerCapacity, int crewCapacity) {
        this.model = model;
        this.passengerCapacity = passengerCapacity;
        this.crewCapacity = crewCapacity;
    }

    @Override
    public String toString() {
        return (" Model: " + this.model + " Passenger seats: " + this.passengerCapacity +
                " Number of crew: " + this.crewCapacity);
    }
}

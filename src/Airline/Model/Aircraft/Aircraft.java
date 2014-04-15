package Airline.Model.Aircraft;

import java.io.Serializable;

abstract public class Aircraft implements Serializable {

    protected String name;
    protected String model;
    protected int passengerCapacity;
    protected int crewCapacity;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public int getCrewCapacity() {
        return crewCapacity;
    }

    public void setCrewCapacity(int crewCapacity) {
        this.crewCapacity = crewCapacity;
    }

    class AircraftCapacityException extends Exception {
    }

    ;
}

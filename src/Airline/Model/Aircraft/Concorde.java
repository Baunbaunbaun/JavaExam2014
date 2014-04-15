package Airline.Model.Aircraft;

public class Concorde extends Aircraft {

    public Concorde(String name) {
        super(name, "Aérospatiale-BAC Concorde", 100, 3);
    }

    public int getNumberOfCrew() {
        return this.crewCapacity;
    }


    public int getPassengerCapacity() {
        return this.passengerCapacity;
    }
}

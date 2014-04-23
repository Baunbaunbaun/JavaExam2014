package Airline.Model.Aircraft;

public class Concorde extends Aircraft {

    public Concorde(String name) {
        super(
                name, // Aircraft name
                "Aérospatiale-BAC Concorde", // model
                100, //passenger capacity
                3 // crew capacity
        );
    }
}
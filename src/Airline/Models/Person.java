package Airline.Models;

import java.util.HashMap;
import java.util.UUID;

abstract public class Person {

    public String name;
    public HashMap<Flight, UUID> bookings;

    //constructor
    public Person(String name) {
        this.name = name;
    }
}

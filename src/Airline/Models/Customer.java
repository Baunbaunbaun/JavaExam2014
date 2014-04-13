package Airline.Models;

import java.util.HashMap;
import java.util.UUID;

public class Customer extends Person {

    public HashMap<Flight, UUID> bookings;

    public Customer(String name) {
        super(name);
    }

    public void checkIn(Customer customer, Flight flight) {
        //check if the flight is booked for this passenger
    }
}

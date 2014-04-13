package Airline.Models;

import java.util.HashMap;
import java.util.UUID;

public class Customer extends Person {

    public HashMap<Flight, UUID> bookings;

    public Customer(String name) {
        super(name);
    }

    public boolean checkIn(Customer customer, Flight flight) {
        if (!this.bookings.containsKey(flight)) {
            this.bookings.put(flight, UUID.randomUUID());
            return true;
        } else {
            return false;
        }
    }
}

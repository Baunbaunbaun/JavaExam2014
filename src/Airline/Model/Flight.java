package Airline.Model;

import Airline.Model.Aircraft.Aircraft;
import Airline.Model.Person.Customer;
import Airline.Model.Person.Staff;
import Airline.Program;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Flight implements Serializable {

    private String id;
    private HashSet<Staff> crew;
    private HashMap<Customer, Booking> bookings;
    // TODO:
    // handle capacity and null pointers if no airfract is assigned to the flight.
    private Aircraft aircraft;
    private Airport from;
    private Airport to;
    private int time;

    //constructor
    public Flight(String id, Airport from, Airport to, int time) throws Exception {
        this.setId(id);
        this.from = from;
        this.to = to;
        this.time = time;
    }

    public HashMap<Customer, Booking> getBookings() {
        return bookings;
    }

    public void setId(String id) throws Exception {
        // 2 letters and 4 digitsm eg EK2031
        if (id.matches("\\w\\w\\d\\d\\d\\d")) {
            this.id = id.toUpperCase();
        } else {
            throw new Exception("Illigal FlightId");
        }
    }

    public boolean addStaff(Staff staff) {

        if (this.crew.size() < this.aircraft.getCrewCapacity())
            return this.crew.add(staff);
        return false;
    }


    public void bookSeat(Customer customer) {

        // REMEMBER NULLPOINTER NO AIRCRAFT EX
        // Exceptions would be nice, to handle more cases of failures here
        // 1. already booked
        // 2. no space
        // 3. aircraft no assigned to flight

        if (this.availableSeats() > 0)
            this.bookings.put(customer, new Booking((customer)));
    }

    public void checkIn(Customer customer) {
        this.bookings.get(customer).checkIn();
    }


    //get number of available seats
    private int availableSeats() {
        return (this.aircraft.getPassengerCapacity() - this.bookings.size());
    }

    //save method
    public boolean save() {
        Program.dataStore.saveFlight(this);
        return true;
    }

    private class Booking implements Serializable {

        private Customer customer;
        private long time;
        private boolean checkedIn = false;

        protected Booking(Customer customer) {
            this.customer = customer;
            this.time = System.currentTimeMillis() / 1000L;
        }

        public void checkIn() {
            this.checkedIn = true;
        }


    }
}
